package org.hillel.hm31;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

    public class TestUserMusicI {

        private final WebDriver driver = new OperaDriver();

        @BeforeClass
        public static void beforeAll() {
            System.setProperty("webdriver.opera.driver", "C:\\IT\\QA\\webdrivers\\opera\\operadriver.exe");
        }

        @After
        public void closeBrowser() {
            driver.quit();
        }


        @Test
        public void playSong() throws Throwable{
            driver.get("https://music.i.ua/");
            driver.manage().window().maximize();
            WebElement logo = driver.findElement(By.xpath("//*[@id=\"header_overall\"]/div[1]/a"));
            Assert.assertEquals("https://www.i.ua/", logo.getAttribute("href"));


            driver.findElement(By.xpath("//*[@id=\"header_overall\"]/div[1]/ul[3]/li[1]/a")).click();
            WebElement email = driver.findElement(By.name("login"));
            WebElement password = driver.findElement(By.name("pass"));
            WebElement loginForm = driver.findElement(By.name("lFloat"));
            email.sendKeys("manchukd24@gmail.com");
            password.sendKeys("260864Dasha");
            loginForm.submit();
            WebElement myName = driver.findElement(By.xpath("//*[@id=\"header_overall\"]/div[1]/ul[3]/li[1]/a/span"));
            Assert.assertEquals("Дарья", myName.getText());


            driver.findElement(By.name("words")).sendKeys("Могилевская");
            WebElement find = driver.findElement(By.name("search"));
            find.submit();
            WebElement song = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[2]/div/div[5]/table/tbody/tr[2]/td[3]/a"));
            Assert.assertEquals("Романс о любви", song.getText());


            WebElement playSong = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[2]/div/div[5]/table/tbody/tr[3]/td[1]/a/span"));
            playSong.click();

            switchDriverToPlayerWindow();

            WebElement currentSong = driver.findElement(By.xpath("//*[@id=\"songRow55860\"]/td[2]/div/span"));
            Assert.assertEquals("Наталья Могилевская - Этот танец", currentSong.getText());
            Thread.sleep(20000);
        }

        private void switchDriverToPlayerWindow() {
            for(String handle : driver.getWindowHandles()) {
                if(!handle.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(handle);
                }
            }
        }
    }





