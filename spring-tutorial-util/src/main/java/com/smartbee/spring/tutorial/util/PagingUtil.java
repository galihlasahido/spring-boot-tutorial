package com.smartbee.spring.tutorial.util;

import org.springframework.data.domain.Page;

public class PagingUtil {

	public static Pagination getPagination(Page<?> page, String baseUrl) {

		int current = page.getNumber();
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		Pagination pagination = new Pagination();
		pagination.setDeploymentLog(page);
		pagination.setDeploymentData(page.getContent());
		pagination.setBeginIndex(begin);
		pagination.setEndIndex(end);
		pagination.setCurrentIndex(current);
		pagination.setBaseUrl(baseUrl);

		return pagination;
	}

}
