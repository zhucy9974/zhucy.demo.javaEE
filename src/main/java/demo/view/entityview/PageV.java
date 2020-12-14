package demo.view.entityview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import demo.config.constants.AppConstants;

public class PageV<T> {
	private int currentPage;
	private int totalPages;
	private long totalEl;
	private List<T> elements;
	private List<Integer> pagesOfNav = new ArrayList<>();

	public PageV(Page page) {
		this.currentPage = page.getNumber() + 1;
		this.totalPages = page.getTotalPages();
		this.totalEl = page.getTotalElements();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalEl() {
		return totalEl;
	}

	public void setTotalEl(long totalEl) {
		this.totalEl = totalEl;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public List<Integer> getPagesOfNav() {
		return pagesOfNav;
	}

	public void setPagesOfNav(List<Integer> pagesOfNav) {
		this.pagesOfNav = pagesOfNav;
	}

}
