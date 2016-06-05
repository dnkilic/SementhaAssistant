package com.dnkilic.application41;

import android.app.Activity;
import android.content.Intent;
import android.provider.AlarmClock;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dnkilic on 04/06/16.
 */
public class CommandAnalyzer {

    /*
    * Hüseyin wikipedia'dan
    * Hüseyin makale okutsun Resmigazeteden birşey okutsun resmi gazete oku
    * Aslıhan SMS gönderme
    * Aslıhan kameranın çektiği fotoğrafı kaydetme sorunu çözülmeli
    * Suzan Naber/Naasılsın/Hal hatır sormaya cevaplar
    * Suzan Background'a üç tane resim bulunacak oylama ile seçeceğiz 100x100
    * Ahmet Y. 1.telefondaki mp3leri bulacak + herhangibirisini çalacak
    * Cihan backgroundaddan arama yapacaksın 1. rehber kayıtları bir map'e koyulur 2. kullanıcının söylediği isim tanınır 3. arama yapılır
    * Mesut openweatherapi hava durumu nasıl
    * Barış kullanıcılar tarafından yapılan sorguları veritabanına kaydetme
    * Barış dolar ne kadar euro ne kadar
    * Barış türkçedeki isimler listesi bulunmalı, veritabanı şeklinde androidde kullanılacak hale geitirilmeli, sorgu yapıldığı durumda kaç saniye sürüyor analiz edilsin
    * Şenel ankaradan izmire nasıl gidilir 81
    * Onur neredeyim sorusuna cevap versin
    * Ahmet Ç. glosbe api'sini kullanarak translate işlemi yapılmalı, x çevir, glosbe
    * Bilal Şamil haritayla ilgili birşeyler yapılacak
    * Ece etkinlik oluşturma, hatırlatıcı oluştur
    * Ece bebeğime hangi ismi vereyim
    * Ece oğluma hangi ismimi vereyim
    * Ece kızıma hangi ismi vereyim
    * Ece güzel üç  buton ve küçük buton resmi bulunacak 9patch
    * Ece diyaloglar için güzel bir renk tercihi yapılmalı (3)
    * Engin application bar'ın rengi simgesi, uygulama simgesi ayarlanmalı
    * */

    //TODO işlem 1 : buraya menünün int değeri eklenir
    public final static int EXECUTE_UNKNOWN_COMMAND = -1;
    public final static int OPEN_BLUETOOTH = 0;
    public final static int CLOSE_BLUETOOTH = 1;
    public final static int MAKE_FUNNY_ANSWER = 2;
    public final static int CLOSE_APPLICATION = 3;
    public final static int SET_ALARM = 4;
    public final static int OPEN_CAMERA = 5;

    private Activity mAct;

    public CommandAnalyzer(Activity activty)
    {
        mAct = activty;
    }

    public Command analyze(String result) {
        Command command = null;

        //TODO işlem 2: buraya hangi kelimeler tanınırsa yapılacak Komut create edilir onu implement edeceksiniz
        if(result.contains("seviyorum") || result.contains("sevgi")
                || result.contains("sev") || result.contains("hoşlan")
                || result.contains("aşk") || result.contains("aşık"))
        {
            command = new Command("Duygularımız karşılıklı canım", CommandAnalyzer.MAKE_FUNNY_ANSWER);
        }
        else if(result.contains("günaydın") || result.contains("hayırlı sabahlar")
                || result.contains("sabah şerifleriniz hayır olsun") || result.contains("iyi uyudun mu")
                || result.contains("iyi sabahlar"))
        {
            command = new Command("Günaydın aşk kuşum", CommandAnalyzer.MAKE_FUNNY_ANSWER);
        }
        else if(result.contains("evlen") || result.contains("kırma beni evlen benle"))
        {
            command = new Command("Bluzun pembe evlen benle", CommandAnalyzer.MAKE_FUNNY_ANSWER);
        }
        else if(result.contains("akbil") || result.contains("akbili fazla olan var mı")
                || result.contains("akbil akbil akbil") || result.contains("akbili benim yerime tıklatacak olan var mı")
                )
        {
            command = new Command("ne demek abla sen iste yeter", CommandAnalyzer.MAKE_FUNNY_ANSWER);
        }
        else if(result.contains("bluetooth") && result.contains("aç"))
        {
            command = new Command("Tamam bluetooth'u açıyorum kanka", CommandAnalyzer.OPEN_BLUETOOTH);
        }
        else if(result.contains("bluetooth") && result.contains("kapa"))
        {
            command = new Command("Tamam bluetooth'u kapatıyorum kanka", CommandAnalyzer.CLOSE_BLUETOOTH);
        }
        else if(result.contains("uygulamayı kapat") || result.contains("uygulama kapat"))
        {
            command = new Command("Kalbimi kırıyorsun. Ben annemin evine gidiyorum.", CommandAnalyzer.CLOSE_APPLICATION);
        }
        else if(result.contains("alarm kur") || result.contains("alarm ayarla") || result.contains("alarm")
                || result.contains("saat kur") || result.contains("beni uyandır"))
        {
            String str = result.replaceAll("[^\\d]", " ").trim();
            String [] time;
            time = str.split(" ");

            if(time.length == 2)
            {
                setAlarm(time[0], time[1]);
                command = new Command(time[0] + ":" + time[1] + " alarmı kuruldu"  , CommandAnalyzer.SET_ALARM);
            }
            else if(time.length == 1)
            {
                if(time[0].isEmpty())
                {
                    command = new Command("Alarmı kurmak için saati söylemelisin.", CommandAnalyzer.SET_ALARM);
                }
                else
                {
                    setAlarm(time[0], "0");
                    command = new Command(time[0] + " alarmı kuruldu", CommandAnalyzer.SET_ALARM);
                }
            }
            else
            {
                command = new Command("Alarmı kurmak için saati söylemelisin.", CommandAnalyzer.SET_ALARM);
            }
        }
       else if(result.contains("kamera") || result.contains("kamera aç") ||
                result.contains("selfie")|| result.contains("resim çek") ||
                result.contains("fotoğraf") || result.contains("öz çekim") )
        {
                command = new Command("Kamerayı açıyorum", CommandAnalyzer.OPEN_CAMERA);
        }

        /* else if(result.contains(""))
        {

        }*/
        else
        {
            command = new Command("Bu komutla ilgili şuan bir işlem yapamıyorum ama kısa sürede yapıyor olabileceğim. Teşekkürler gülücük", CommandAnalyzer.EXECUTE_UNKNOWN_COMMAND);
        }

        return command;
    }

    private void setAlarm(String hour, String minute) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(hour));
        i.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(minute));
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        mAct.startActivity(i);
    }
}