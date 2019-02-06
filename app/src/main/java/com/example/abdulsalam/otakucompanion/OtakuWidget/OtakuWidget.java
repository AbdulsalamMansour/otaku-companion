package com.example.abdulsalam.otakucompanion.OtakuWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.abdulsalam.otakucompanion.Model.SharedClasses.AnimeDetailed;
import com.example.abdulsalam.otakucompanion.R;

/**
 * Implementation of App Widget functionality.
 */
public class OtakuWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String synopsis, AnimeDetailed animeDetailed, int appWidgetId) {
        StringBuilder myWidgetTxt =new StringBuilder();
        myWidgetTxt.append(animeDetailed.getTitle()).append("\n\n\n");
        myWidgetTxt.append(synopsis);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.oatku_widget);
        views.setTextViewText(R.id.appwidget_text, myWidgetTxt);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    public static void updateAnimeSynopsis(Context context, AppWidgetManager appWidgetManager, String synopsis, AnimeDetailed animeDetailed, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager,synopsis, animeDetailed, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

