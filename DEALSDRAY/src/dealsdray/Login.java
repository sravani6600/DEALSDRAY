package dealsdray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class Login {
	
	@Test
	public void login() throws IOException, InterruptedException {
		WebDriver driver=new ChromeDriver();
		//implicitly wait statement
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		//maximize the browsers
		driver.manage().window().maximize();
		driver.get("https://demo.dealsdray.com/");
		//read data from excel
		FileInputStream fis=new FileInputStream("./testdata/demo-data.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("login");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String username = cell.getStringCellValue();
		Cell cell1 = row.getCell(1);
		String password = cell1.getStringCellValue();
		//enter username
		driver.findElement(By.name("username")).sendKeys(username);
		//enter pssword
		driver.findElement(By.name("password")).sendKeys(password);
		//click on login button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		//screenshot
		TakesScreenshot ts=(TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./screenshot/login.png");
		FileHandler.copy(temp, perm);
		//quit browser
		driver.quit();
	}
	

}
