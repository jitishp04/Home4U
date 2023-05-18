#ifndef SONG_INFO_H
#define SONG_INFO_H


class SongInfo{
    public:

        SongInfo(String name, String fileName){
            this->name = name;
            this->fileName = fileName;
        }


        String getName(){
            return name;
        }

        String getFileName(){
            return fileName;
        }

    private:
        String name;
        String fileName;
};


#endif