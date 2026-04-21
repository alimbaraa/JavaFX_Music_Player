package models;

public class Song {
    private String cover;
    private String name;
    private String artist;
    private String MP3;
    private String length;
    private Boolean liked;

    public Song(String cover, String name, String artist, String MP3, String length, Boolean liked) {
        this.cover = cover;
        this.name = name;
        this.artist = artist;
        this.MP3 = MP3;
        this.length = length;
        this.liked = liked;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getMP3() {
        return MP3;
    }

    public void setMP3(String MP3) {
        this.MP3 = MP3;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String songLength) {
        this.length = songLength;
    }
}
