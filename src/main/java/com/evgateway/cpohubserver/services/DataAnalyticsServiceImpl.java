package com.evgateway.cpohubserver.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.Utils.Utils;
import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.form.InstantDateRange;
import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class DataAnalyticsServiceImpl implements DataAnalyticsService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public byte[] exportData(Map<String, String> filters) throws IOException, ParseException {

		// Fetch the data and generate the Excel file
		List<CPOCdrModel> data = fetchDataFromDatabase(filters);

		try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Report");

			// Create a CellStyle for the header row
			XSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(255, 255, 0), null)); // Yellow color
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Font boldFont = workbook.createFont();
			boldFont.setBold(true);
			headerCellStyle.setFont(boldFont);
			// Add headers and apply the style
			Row headerRow = sheet.createRow(0);
			String[] columns = { "AuthReferenceId", "CpoCode", "CdrId", "SessionId", "StartDateTime", "EndDateTime",
					"LocationName", "LocationAddress", "EvseId", "EmspCode", "EmspTokenType", "EmspTokenId",
					"TotalEnergy", "TotalTime", "TotalCost", };
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle); // Apply the yellow background style
			}

			int rowNum = 1;
			for (CPOCdrModel session : data) {
				Row row = sheet.createRow(rowNum++);

				for (int i = 0; i < columns.length; i++) {

					row.createCell(0).setCellValue(session.getAuthorization_reference());
					row.createCell(1).setCellValue(session.getCpo_party_id() + "-" + session.getCpo_country_code());
					row.createCell(2).setCellValue(session.getId());
					row.createCell(3).setCellValue(session.getSession_id());
					row.createCell(4).setCellValue(DateTimeFormatter.ISO_INSTANT.format(session.getStart_date_time()));
					row.createCell(5).setCellValue(DateTimeFormatter.ISO_INSTANT.format(session.getEnd_date_time()));
					row.createCell(6).setCellValue(session.getCdr_location().getName());
					row.createCell(7).setCellValue(session.getCdr_location().getAddress() + ", "
							+ session.getCdr_location().getCity() + ", " + session.getCdr_location().getPostal_code()
							+ ", " + session.getCdr_location().getCountry());
					row.createCell(8).setCellValue(session.getCdr_location().getEvse_id());
					row.createCell(9).setCellValue(session.getEmsp_party_id() + "-" + session.getEmsp_country_code());
					row.createCell(10).setCellValue(session.getCdr_token().getType());
					row.createCell(11).setCellValue(session.getCdr_token().getUid());
					row.createCell(12).setCellValue(session.getTotal_energy());
					double hours = session.getTotal_time();

// Convert to seconds, round to nearest minute (i.e., nearest 60 seconds)
int totalSeconds = (int) Math.round(hours * 3600);
int roundedToMinuteSeconds = (int) (Math.round(totalSeconds / 60.0) * 60);

// Convert back to hours (as Utils.getTimeFormate expects hours)
double roundedHours = roundedToMinuteSeconds / 3600.0;

// Set formatted value into Excel cell
row.createCell(13).setCellValue(Utils.getTimeFormate(roundedHours));

					// row.createCell(13).setCellValue(Utils.getTimeFormate(session.getTotal_time()));
					row.createCell(14).setCellValue(session.getTotal_cost().getIncl_vat());
				}
			}
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}
			workbook.write(out);
			return out.toByteArray();
		}
	}

	public List<CPOCdrModel> fetchDataFromDatabase(Map<String, String> filters) throws ParseException {
		Query query = new Query();
		// Parse start and end date strings into LocalDateTime or Date objects

		InstantDateRange dateRange = getDateRange(filters.get("range").toString());

		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			query.addCriteria(
					Criteria.where("end_date_time").gte(dateRange.getStartInstant()).lte(dateRange.getEndInstant()))
					.with(Sort.by(Sort.Direction.DESC, "end_date_time"));

			if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
				Criteria filterCriteria = new Criteria();
				filterCriteria.and("emsp_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
				filterCriteria.and("emsp_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
				query.addCriteria(filterCriteria);

			} else if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
				Criteria filterCriteria = new Criteria();
				filterCriteria.and("cpo_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
				filterCriteria.and("cpo_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
				query.addCriteria(filterCriteria);

			} else if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

				if (filters.get("basedOn").toString().equalsIgnoreCase("EMSP")) {
					Criteria filterCriteria = new Criteria();
					filterCriteria.and("emsp_party_id").regex(".*" + filters.get("party_id").toString() + ".*", "i");
					query.addCriteria(filterCriteria);

				} else if (filters.get("basedOn").toString().equalsIgnoreCase("CPO")) {

					Criteria filterCriteria = new Criteria();
					filterCriteria.and("cpo_party_id").regex(".*" + filters.get("party_id").toString() + ".*", "i");

					query.addCriteria(filterCriteria);
				}

			}

		}

		return mongoTemplate.find(query, CPOCdrModel.class);

	}

	@Override
	public PageResult<CPOCdrModel> getTableData(int pagesize, int page, Map<String, String> filters)
			throws ParseException {
		Query query = new Query();

		InstantDateRange dateRange = getDateRange(filters.get("range").toString());

		Pageable pageable = PageRequest.of(page, pagesize);

		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			query.addCriteria(
					Criteria.where("end_date_time").gte(dateRange.getStartInstant()).lte(dateRange.getEndInstant()))
					.with(Sort.by(Sort.Direction.DESC, "end_date_time"));

			if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
				Criteria filterCriteria = new Criteria();
				filterCriteria.and("emsp_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
				filterCriteria.and("emsp_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
				query.addCriteria(filterCriteria);

			} else if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
				Criteria filterCriteria = new Criteria();
				filterCriteria.and("cpo_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
				filterCriteria.and("cpo_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
				query.addCriteria(filterCriteria);

			} else if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

				if (filters.get("basedOn").toString().equalsIgnoreCase("EMSP")) {
					Criteria filterCriteria = new Criteria();
					filterCriteria.and("emsp_party_id").regex(".*" + filters.get("party_id").toString() + ".*", "i");
					query.addCriteria(filterCriteria);

				} else if (filters.get("basedOn").toString().equalsIgnoreCase("CPO")) {

					Criteria filterCriteria = new Criteria();
					filterCriteria.and("cpo_party_id").regex(".*" + filters.get("party_id").toString() + ".*", "i");

					query.addCriteria(filterCriteria);
				}

			}

		}

		List<CPOCdrModel> cdrs = mongoTemplate.find(query.with(pageable), CPOCdrModel.class);

		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOCdrModel.class);

		Page<CPOCdrModel> cdrss = new PageImpl<>(cdrs, pageable, count);

		return new PageResult<>(cdrss);

	}

	public InstantDateRange getDateRange(String period) {

		ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC); // Adjust ZoneOffset as needed
		ZonedDateTime startDateTime;
		ZonedDateTime endDateTime;

		switch (period.toLowerCase()) {
		case "this_month":
			startDateTime = now.withDayOfMonth(1).toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.plusMonths(1).withDayOfMonth(1).toLocalDate().atStartOfDay(now.getZone()).minusNanos(1);
			break;

		case "last_month":
			startDateTime = now.minusMonths(1).withDayOfMonth(1).toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.withDayOfMonth(1).toLocalDate().atStartOfDay(now.getZone()).minusNanos(1);
			break;

		case "this_week":
			startDateTime = now.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.with(DayOfWeek.SUNDAY).toLocalDate().atTime(LocalTime.MAX).atZone(now.getZone());
			break;

		case "last_week":
			startDateTime = now.minusWeeks(1).with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.minusWeeks(1).with(DayOfWeek.SUNDAY).toLocalDate().atTime(LocalTime.MAX)
					.atZone(now.getZone());
			break;

		case "today":
			startDateTime = now.toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.toLocalDate().atTime(LocalTime.MAX).atZone(now.getZone());
			break;

		case "yesterday":
			startDateTime = now.minusDays(1).toLocalDate().atStartOfDay(now.getZone());
			endDateTime = now.minusDays(1).toLocalDate().atTime(LocalTime.MAX).atZone(now.getZone());
			break;

		default:
			throw new IllegalArgumentException("Invalid period: " + period);
		}

		// Convert ZonedDateTime to Instant
		return new InstantDateRange(startDateTime.toInstant(), endDateTime.toInstant());
	}

	@Override
	public List<Map<String, Object>> getRoleInfo(String role) {
		Query query = new Query();

		if (role.equalsIgnoreCase("CPO")) {
			query.addCriteria(Criteria.where("role").is("CPO"));

		} else if (role.equalsIgnoreCase("EMSP")) {
			query.addCriteria(Criteria.where("role").is("EMSP"));

		} else {

			throw new IllegalArgumentException("Invalid role: " + role);
		}
		query.fields().include("party_id").include("country_code");

		List<CPOHUBPartnerModel> partners = mongoTemplate.find(query, CPOHUBPartnerModel.class);

		// Process results using a traditional loop
		List<Map<String, Object>> result = new ArrayList<>();
		for (CPOHUBPartnerModel partner : partners) {
			Map<String, Object> partnerMap = new HashMap<>();
			partnerMap.put("party_id", partner.getParty_id());
			partnerMap.put("country_code", partner.getCountry_code());
			result.add(partnerMap);
		}

		return result;
	}

}
