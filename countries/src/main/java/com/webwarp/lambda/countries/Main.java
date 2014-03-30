package com.webwarp.lambda.countries;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author bruno borges
 */
public class Main {

    public static void main(String[] args) {
        final String formatOutput = "Country : %s \t\t\t\t:\t Country Code : %s";

        List<String> list = Arrays.asList(Locale.getISOCountries())
                .stream()
                .map(c -> new Locale("", c))
                .sorted((c0, c1) -> c0.getDisplayCountry().compareTo(c1.getDisplayCountry()))
                .map(l -> String.format(formatOutput, l.getDisplayCountry(), l.getCountry()))
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

}
