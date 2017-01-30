package com.web.viewmodels;

public class Pagination {

    private int selectedPage;
    private int pageCount;
    private String urlPattern;

    public Pagination() {
    }

    /**
     * Create pagination class for jsp pagination fragment
     * Recommended call your variable 'pagination'
     * Jsp fragment will look for 'pagination' variable
     *
     * @param selectedPage page currently selected by user. will be highlighted with bootstrap
     * @param pageCount    total count of pages. count of "buttons" for pagination
     * @param urlPattern   urlPattern page numbers will generate url-link for each button.
     */
    public Pagination(int selectedPage, int pageCount, String urlPattern) {
        this.selectedPage = selectedPage;
        this.pageCount = pageCount;
        this.urlPattern = urlPattern;
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage = selectedPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
