
package com.example.project;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith({ScreenShooterExtension.class})
class DownloadBookIT {

  @BeforeAll
  static void init() {
    Configuration.headless = true;
    Configuration.browserSize = "1920x1080";
  }

  @Test
  void givenFlipBookUrl_whenGoThere_thenGeneratePages() {
    var url = "";
    open(url);
    int numPages = 126;
    var bookPage = new BookPage();
    screenshot(String.format("page%s", "00"));
    for (int i = 0; i < numPages; i++) {
      bookPage.nextPageWithCssSelector();
      Selenide.sleep(1000);
      screenshot(String.format("page%d", i));
    }
  }

  class BookPage {
    public void nextPageWithCssClass() {
      $(By.className("flip_button_right")).click();
    }

    public void nextPageWithCssSelector() {
      $(By.cssSelector("div:nth-child(11) > img:nth-child(1)")).click();
    }
  }
}
