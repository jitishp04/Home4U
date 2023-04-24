class SongDownloader{
    public:
        SongDownloader(void (*songStreamCallback)());
        String getSongInfo();
        void streamSong(String fileName);
        bool readSongSample(uint8_t* outputArray);
};