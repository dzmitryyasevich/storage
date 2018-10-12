package storage.export.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import storage.export.view.CsvView;

import java.util.Locale;

public class CsvViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {

        return new CsvView();
    }
}
