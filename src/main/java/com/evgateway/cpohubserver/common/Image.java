package com.evgateway.cpohubserver.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Image {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String url;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String thumbnail;

	@JsonIgnore
	private String category;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String type;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int width;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int height;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Image [url=" + url + ", thumbnail=" + thumbnail + ", category=" + category + ", type=" + type
				+ ", width=" + width + ", height=" + height + "]";
	}

}
