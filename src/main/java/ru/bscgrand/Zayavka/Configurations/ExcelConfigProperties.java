package ru.bscgrand.Zayavka.Configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "excel.zayavki")
public class ExcelConfigProperties {
    private String dirPathExcelFiles;
    private String dirPathArchivePath;
    private String dateFromSearch;


    public String getDirPathExcelFiles() {
        return dirPathExcelFiles;
    }

    public String getDirPathArchivePath() {
        return dirPathArchivePath;
    }

    public String getDateFromSearch() {
        return dateFromSearch;
    }

    public void setDirPathExcelFiles(String dirPathExcelFiles) {
        this.dirPathExcelFiles = dirPathExcelFiles;
    }

    public void setDirPathArchivePath(String dirPathArchivePath) {
        this.dirPathArchivePath = dirPathArchivePath;
    }

    public void setDateFromSearch(String dateFromSearch) {
        this.dateFromSearch = dateFromSearch;
    }
}
