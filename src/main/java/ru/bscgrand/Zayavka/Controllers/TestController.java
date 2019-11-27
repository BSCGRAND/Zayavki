package ru.bscgrand.Zayavka.Controllers;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.Repositories.UserRepository;
import ru.bscgrand.Zayavka.Models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    @GetMapping("/exceltest")
    public String getExcel() throws IOException{
        return readFromExcel("E:\\Заявка Бурение 1.xlsx");
    }

    public String readFromExcel(String file) throws IOException {
        //String printexcel;
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sh = wb.getSheet("Заявка");
        XSSFRow row = sh.getRow(1);
        //printexcel = row.getCell(1);
        return row.getCell(2).getStringCellValue();

    }
}
