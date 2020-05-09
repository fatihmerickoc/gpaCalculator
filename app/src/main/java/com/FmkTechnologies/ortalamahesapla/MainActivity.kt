package com.FmkTechnologies.ortalamahesapla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {



    private  val DERSLER = arrayOf("Matematik","Fizik","Türkçe","Felsefe","Aritmetik","Tarih")

    private  var tumderslerinBilgileri : ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DERSLER)
        etDersAd.setAdapter(adapter)

        btnDersEkle.setOnClickListener {
            if(!etDersAd.text.isNullOrEmpty()){
                var inflater = LayoutInflater.from(this)
                var yeniDersView =    inflater.inflate(R.layout.yeni_ders_layout,null)

                var dersAdi = etDersAd.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarf  = spnDersNot.selectedItem.toString()


                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(spnDersKredi.selectedItemPosition)
                yeniDersView.spnYeniDersNot.setSelection(spnDersNot.selectedItemPosition)

                yeniDersView.btnDersSil.setOnClickListener {
                    rootlayout.removeView(yeniDersView)
                    if (rootlayout.childCount > 0 ){
                        btn_hesaplaOrtalama.visibility = View.VISIBLE


                    }else{
                        btn_hesaplaOrtalama.visibility = View.INVISIBLE

                    }



                }

                rootlayout.addView(yeniDersView)
                btn_hesaplaOrtalama.visibility = View.VISIBLE
                etDersAd.setText("")
                spnDersKredi.setSelection(0)
                spnDersNot.setSelection(0)

            }else{
                //Toast.makeText(this,"Lütfen ders adını giriniz",Toast.LENGTH_SHORT).show()
                Toasty.error(this, "Lütfen ders adını giriniz", Toast.LENGTH_SHORT, true).show();

            }




            }































    }

   /* fun spinnerIndexDegerBul(spinner: Spinner,aranacakAd : String): Int{
        var index = 0
        for (i in 0..spinner.count){
            if (spinner.getItemAtPosition(i).equals(aranacakAd)){
                index = i
                break
            }
        }
        return index


    }

    */


    fun ortalamaHesapla(view: View) {

        var toplamNot :Double = 0.0
        var toplamKredi :Double = 0.0

        for (i in 0..rootlayout.childCount -1){
            var teksatir = rootlayout.getChildAt(i)
            var geciciDers = Dersler(teksatir.etYeniDersAd.text.toString()
                ,((teksatir.spnYeniDersKredi.selectedItemPosition)+1).toString(),
                teksatir.spnYeniDersNot.selectedItem.toString())

            tumderslerinBilgileri.add(geciciDers)


        }
        for (oankiDers in tumderslerinBilgileri){

        toplamNot+=harfiNotaCevir(oankiDers.dersHarfNoT) * oankiDers.dersKredi.toDouble()

            toplamKredi += oankiDers.dersKredi.toDouble()

        }
      //  Toast.makeText(this,"ORTALAMA : ${(toplamNot / toplamKredi)}",Toast.LENGTH_LONG).show()


        Toasty.success(this, "ORTALAMA : ${(toplamNot / toplamKredi)}", Toast.LENGTH_SHORT, true).show();
        //FancyToast.makeText(this,"ORTALAMA : ${(toplamNot / toplamKredi)}",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()

        tumderslerinBilgileri.clear()

    }
    fun harfiNotaCevir(gelenNotHarf: String):Double{
        var deger = 0.0
        when(gelenNotHarf){
            "AA"-> deger = 4.0
            "BA"-> deger = 3.5
            "BB"-> deger = 3.0
            "CB"-> deger = 2.5
            "CC"-> deger = 2.0
            "DC"-> deger = 1.5
            "DD"-> deger = 1.0
            "FF"-> deger = 0.0

        }
        return  deger
    }




}
