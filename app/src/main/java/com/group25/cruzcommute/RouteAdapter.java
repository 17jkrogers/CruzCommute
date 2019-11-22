package com.group25.cruzcommute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RouteAdapter extends ArrayAdapter<Route> {

    private ArrayList<Route> routeList;
    private Context schedctx;
    private int layoutId;

    public RouteAdapter(Context ctx, int rId, ArrayList<Route> routes){
        super(ctx, rId, routes);
        schedctx = ctx;
        layoutId = rId;
        routeList = routes;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewG){
        LayoutInflater layoutInflater = LayoutInflater.from(schedctx);

        View mView = layoutInflater.inflate(layoutId, null, false);

        TextView routeNum = mView.findViewById(R.id.routeNumberText);
        TextView routeSlow = mView.findViewById(R.id.routeSlowdown);
        TextView routeCong = mView.findViewById(R.id.routeCongestion);

        Route route = routeList.get(pos);

        routeNum.setText(route.getRouteNum());
        routeSlow.setText("" + route.getSlowdown());
        routeCong.setText(route.getCongestion());

        return mView;
    }
}
