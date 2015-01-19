#Must add the logger and appender in log4j.xml

#appender
<appender name="hdx-ckan-updater-job" class="org.apache.log4j.RollingFileAppender">
    <!-- <param name="file" value="D:\\HDX\\logs\\hdx.log" /> -->
    <!-- <param name="file" value="${HDX_FOLDER}/logs/hdx.log" /> -->
    <param name="file" value="/opt/ocha/hdx/logs/ckanUpdaterJob.log" />
    <param name="MaxFileSize" value="500KB" />
    <param name="MaxBackupIndex" value="20" />
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern" value="%-5p %d{yy-MM-dd HH:mm:ss}: %m (%F:%L) [%t]%n" />
    </layout>
  </appender>
  
  
 #logger
  <logger name="ckan-updater-logger" additivity="false">
    <level value="debug" />
    <appender-ref ref="hdx-ckan-updater-job" />
    <appender-ref ref="hdx-console" />
  </logger>