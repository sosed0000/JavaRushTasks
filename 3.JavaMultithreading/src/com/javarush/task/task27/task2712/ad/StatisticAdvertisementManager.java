package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public List<Advertisement> getAdvertisements(boolean isActive) {
//        return new ArrayList<>(advertisementStorage.list().stream().filter(a -> isActive ? a.getHits() > 0 : a.getHits() == 0).toList());
        List<Advertisement> activeVideos = new ArrayList<>();
        List<Advertisement> notActiveVideos = new ArrayList<>();
        for (Advertisement advertisement:
             advertisementStorage.list()) {
            if (advertisement.getHits() > 0)
                activeVideos.add(advertisement);
            else
                notActiveVideos.add(advertisement);
        }
        return isActive ? activeVideos : notActiveVideos;
    }
}
