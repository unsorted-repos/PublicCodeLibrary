# pip install youtube-dl
import youtube_dl  # Youtube_dl is used for download the video

ydl_opt = {
    "outtmpl": "~/videos/%(title)s.%(ext)s",
    "format": "bestaudio/best",
}  # Here we give some advanced settings. outtmpl is used to define the path of the video that we are going to download


def operation(link):
    """
    Start the download operation
    """
    try:
        with youtube_dl.YoutubeDL(
            ydl_opt
        ) as yd:  # The method YoutubeDL() take one argument which is a dictionary for changing default settings
            video = yd.download([link])  # Start the download
            print(f"video={video}")
        # result.set("Your video has been downloaded !")
    except Exception:
        # result.set("Sorry, we got an error.")
        print("skip")


operation("https://youtube.com/watch?v=5qAFIqBAN0M")
