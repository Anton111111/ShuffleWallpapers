package com.anton111111.shufflewallpapers.di;

import android.app.Application;

import com.anton111111.shufflewallpapers.App;
import com.anton111111.shufflewallpapers.repository.PhotoRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.Declarations.class)
class AppModule {

    @Module
    interface Declarations {
        @Binds
        Application bindApplication(App app);
    }

    @Singleton
    @Provides
    PhotoRepository providePhotoRepository(Application application) {
        return new PhotoRepository(application);
    }
}
