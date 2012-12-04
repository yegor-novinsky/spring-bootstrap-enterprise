package org.andidev.logging.logback.old;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.andidev.logging.logback.colors.BashColor;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author anders
 * 
 * see http://logback.qos.ch/manual/layouts.html#customConversionSpecifier
 */
public class TraceColorConverter extends ClassicConverter {

    static private String separator = ":";

    @Override
    public String convert(ILoggingEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(getColor(event.getLevel()));
        stringBuilder.append(event.getMDCPropertyMap());
        stringBuilder.append(separator);            
        if (event.getMDCPropertyMap().containsKey("username")) {
            stringBuilder.append(event.getMDCPropertyMap().get("username"));
            stringBuilder.append(separator);            
        }
        stringBuilder.append(StringUtils.substringAfterLast(((StackTraceElement) event.getCallerData()[0]).getClassName(), "."));
        stringBuilder.append(separator);
        stringBuilder.append(Integer.toString(((StackTraceElement) event.getCallerData()[0]).getLineNumber()));
        stringBuilder.append(BashColor.COLOR_OFF);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * Returns the appropriate characters to change the color for the specified
     * logging level.
     */
    private String getColor(Level level) {
        //return LevelEncoder.getColor(level);
        return BashColor.PURPLE;
    }
}
