package br.alicefranco.countriesapp;

class Language {
    private String name;
    private String nativeName;

    public Language(String name, String nativeName){
        this.name = name;
        this.nativeName = nativeName;
    }



    public void setName(String name){ this.name = name; }
    public void setNativeName(String nativeName){ this.nativeName = nativeName; }

    public String getName(){ return name; }
    public String getNativeName(){ return nativeName; }
}
