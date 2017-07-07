package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public enum WaitCondition {
    visible(ExpectedConditions::visibilityOfElementLocated),
    clickable(ExpectedConditions::elementToBeClickable),
    presence(ExpectedConditions::presenceOfElementLocated);

    private final Function<By, ExpectedCondition<WebElement>> type;

    WaitCondition(Function<By, ExpectedCondition<WebElement>> type) {
        this.type = type;
    }

    public Function<By, ExpectedCondition<WebElement>> get(){
        return type;
    }
}
