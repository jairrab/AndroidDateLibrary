# AndroidDateLibrary
Date library built for BC

[![](https://jitpack.io/v/jairrab/AndroidDateLibrary.svg)](https://jitpack.io/#jairrab/AndroidDateLibrary)

`implementation 'com.github.jairrab:AndroidDateLibrary:Tag'`

## How to use: 
```kotlin
val dateUtils:DateUtils = DateUtils.getInstance(
            dateFormatPreference = "YYYY-MMMM-dd", //default is device setting
            timeFormatPreference = "hh:mm", //default is "hh:mm"
            startMonthOfYear = 0, //default is 0 or Calendar.JANUARY
            startMonthDay = 1, //default is 1
            firstDayOfWeek = 1, //default is 1 or Calendar.SUNDAY
            locale = Locale.getDefault() //default is Locale.getDefault()
)
```
