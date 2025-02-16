package org.example.imdb.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public final static Map<Long, String> GENRE = Map.ofEntries(
        Map.entry(1L, "Documentary"),
        Map.entry(2L, "Animation"),
        Map.entry(3L, "Comedy"),
        Map.entry(4L, "Short"),
        Map.entry(5L, "Romance"),
        Map.entry(6L, "News"),
        Map.entry(7L, "Drama"),
        Map.entry(8L, "Fantasy"),
        Map.entry(9L, "Horror"),
        Map.entry(10L, "Biography"),
        Map.entry(11L, "Music"),
        Map.entry(12L, "Crime"),
        Map.entry(13L, "Family"),
        Map.entry(14L, "Adventure"),
        Map.entry(15L, "Action"),
        Map.entry(16L, "History"),
        Map.entry(17L, "Mystery"),
        Map.entry(18L, "Musical"),
        Map.entry(19L, "War"),
        Map.entry(20L, "Sci-Fi"),
        Map.entry(21L, "Western"),
        Map.entry(22L, "Thriller"),
        Map.entry(23L, "Sport"),
        Map.entry(24L, "Film-Noir"),
        Map.entry(25L, "Talk-Show"),
        Map.entry(26L, "Game-Show"),
        Map.entry(27L, "Adult"),
        Map.entry(28L, "Reality-TV")
    );

    public final static Map<Long, String> PROFESSION = Map.ofEntries(
            Map.entry(1L, "actor"),
        Map.entry(2L, "actress"),
        Map.entry(3L, "writer"),
        Map.entry(4L, "composer"),
        Map.entry(5L, "music_department"),
        Map.entry(6L, "director"),
        Map.entry(7L, "music_artist"),
        Map.entry(8L, "editor"),
        Map.entry(9L, "cinematographer"),
        Map.entry(10L, "producer"),
        Map.entry(11L, "art_director"),
        Map.entry(12L, "make_up_department"),
        Map.entry(13L, "miscellaneous"),
        Map.entry(14L, "assistant_director"),
        Map.entry(15L, "executive"),
        Map.entry(16L, "camera_department"),
        Map.entry(17L, "sound_department"),
        Map.entry(18L, "stunts"),
        Map.entry(19L, "soundtrack"),
        Map.entry(20L, "special_effects"),
        Map.entry(21L, "production_designer"),
        Map.entry(22L, "editorial_department"),
        Map.entry(23L, "production_manager"),
        Map.entry(24L, "costume_department"),
        Map.entry(25L, "casting_director"),
        Map.entry(26L, "costume_designer"),
        Map.entry(27L, "set_decorator"),
        Map.entry(28L, "art_department"),
        Map.entry(29L, "casting_department"),
        Map.entry(30L, "visual_effects"),
        Map.entry(31L, "location_management"),
        Map.entry(32L, "animation_department"),
        Map.entry(33L, "script_department"),
        Map.entry(34L, "talent_agent"),
        Map.entry(35L, "transportation_department"),
        Map.entry(36L, "manager"),
        Map.entry(37L, "archive_footage"),
        Map.entry(38L, "legal"),
        Map.entry(39L, "publicist"),
        Map.entry(40L, "podcaster"),
        Map.entry(41L, "archive_sound"),
        Map.entry(42L, "choreographer"),
        Map.entry(43L, "accountant"),
        Map.entry(44L, "electrical_department"),
        Map.entry(45L, "assistant"),
        Map.entry(46L, "production_department")
    );
    public final static Map<Long, String> MOVIE_TYPE = Map.ofEntries(
        Map.entry(1L, "short"),
        Map.entry(2L, "movie"),
        Map.entry(3L, "tvShort"),
        Map.entry(4L, "tvMovie"),
        Map.entry(5L, "tvEpisode"),
        Map.entry(6L, "tvSeries"),
        Map.entry(7L, "tvMiniSeries"),
        Map.entry(8L, "tvSpecial"),
        Map.entry(9L, "video")
    );
}
