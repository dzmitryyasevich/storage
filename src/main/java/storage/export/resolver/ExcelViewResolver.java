package storage.export.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import storage.export.view.ExcelView;

import java.util.Locale;

public class ExcelViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {

        return new ExcelView();
    }
}
