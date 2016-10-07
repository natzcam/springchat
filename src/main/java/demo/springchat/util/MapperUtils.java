package demo.springchat.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.dozer.Mapper;

/**
 * Created by nathaniel.a.camomot on 10/6/2015.
 */
public class MapperUtils {

    private MapperUtils() {
    }

    public static <T> List<T> mapIterable(Iterable srcList, Mapper mapper, Class<T> c) {
        List<T> list = new ArrayList<>();
        for (Object elem : srcList) {
            list.add(mapper.map(elem, c));
        }
        return list;
    }

    public static <T> Page<T> mapPage(Page page, Pageable pageable, Mapper mapper, Class<T> c) {
        List<T> list = new ArrayList<>();
        for (Object elem : page.getContent()) {
            list.add(mapper.map(elem, c));
        }
        Page<T> newPage = new PageImpl<>(list,
                pageable, page.getTotalElements());

        return newPage;
    }

    public static <T> Page<T> mapPage(Page page, Pageable pageable, Mapper mapper, Class<T> c, Function<Object[], T> postProcess) {
        List<T> list = new ArrayList<>();
        for (Object elem : page.getContent()) {
            T item = mapper.map(elem, c);
            item = postProcess.apply(new Object[]{elem, item});
            list.add(item);
        }
        Page<T> newPage = new PageImpl<>(list,
                pageable, page.getTotalElements());

        return newPage;
    }
}
