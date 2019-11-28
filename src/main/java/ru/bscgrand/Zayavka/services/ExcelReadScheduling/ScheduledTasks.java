package ru.bscgrand.Zayavka.services.ExcelReadScheduling;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Configurations.ExcelConfigProperties;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;
import ru.bscgrand.Zayavka.services.exelHandling.CopyFromExcel;
import ru.bscgrand.Zayavka.services.exelHandling.ReadExcel;
import ru.bscgrand.Zayavka.services.exelHandling.UpdateDateOfReceiving;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption.*;
import java.time.LocalDate;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class ScheduledTasks {

    //---------------------DI----------------------------------//
    private final CopyFromExcel copyFromExcel;
    private final UpdateDateOfReceiving updateDateOfReceiving;
    private final ReadExcel readExcel;
    private final GoodsRequestRepository goodsRequestRepository;
    private final ExcelConfigProperties excelConfigProperties;

    @Autowired
    public ScheduledTasks(CopyFromExcel copyFromExcel, UpdateDateOfReceiving updateDateOfReceiving,
                          ReadExcel readExcel, GoodsRequestRepository goodsRequestRepository,
                          ExcelConfigProperties excelConfigProperties) {
        this.copyFromExcel = copyFromExcel;
        this.updateDateOfReceiving = updateDateOfReceiving;
        this.readExcel = readExcel;
        this.goodsRequestRepository = goodsRequestRepository;
        this.excelConfigProperties = excelConfigProperties;
    }
    //--------------------------------------------------------//

    @Scheduled(fixedRate = 50000)
    public void readExcel() {
        File dir = new File(excelConfigProperties.getDirPathExcelFiles());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) continue;
            if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("xlsx")) {
                List<GoodsRequest> newGoodsRequests;
                newGoodsRequests = readExcel.read(file);
                String subdivision = readExcel.getSubdivision(file);
                LocalDate lastDate = goodsRequestRepository.getMaxDateBySubdivision(subdivision);
                copyFromExcel.copyNew(lastDate, newGoodsRequests);
                updateDateOfReceiving.update(lastDate, newGoodsRequests);
                try {
                    Path srcFile = Paths.get(file.getAbsolutePath());
                    Path dstFile = Paths.get(excelConfigProperties.getDirPathArchivePath() + "\\" + file.getName()
                            + "." + LocalDate.now() + ".xlsx");
                    Files.move(srcFile, dstFile, REPLACE_EXISTING);
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }
        }
    }
}


