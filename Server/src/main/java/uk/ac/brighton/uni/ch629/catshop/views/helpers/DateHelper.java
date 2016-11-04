package uk.ac.brighton.uni.ch629.catshop.views.helpers;

import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

@HandlebarsHelper
public class DateHelper {
    public String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy @ HH:mm").format(date);
    }
}
