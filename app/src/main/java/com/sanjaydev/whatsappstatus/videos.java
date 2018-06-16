/*
 * Copyright (c) 2018. this file is created or edited by sanjay
 * mail us to kranthi0987@gmail.com
 */

package com.sanjaydev.whatsappstatus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class videos extends Fragment {

    private static final String WHATSAPP_STATUSES_LOCATION = "/storage/emulated/0/WhatsApp/Media/.Statuses";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public videos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_videos, container, false);
        MobileAds.initialize(getActivity(),
                "ca-app-pub-2324532608407659~6719351639");
        AdView mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView = v.findViewById(R.id.videos_recyclerView);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecorationAlbumColumns(
                getResources().getDimensionPixelSize(R.dimen.photos_list_spacing), 2));
        getListFiles(new File(WHATSAPP_STATUSES_LOCATION));
        videoRecyclerAdapter ra = new videoRecyclerAdapter(this.getListFiles(new File(WHATSAPP_STATUSES_LOCATION)), getActivity());
        recyclerView.setAdapter(ra);
        return v;
    }

    private ArrayList<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files;
        files = parentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".mp4") ||
                        file.getName().endsWith(".gif")) {
                    if (!inFiles.contains(file))
                        inFiles.add(file);
                }
            }
        }
        return inFiles;
    }


}
