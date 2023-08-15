1. Prikazati sva korisnička imena i knjige koje je svaki od njih iznajmio.

select korisnik.KORISNICKO_IME, iznajmljivanje.Id_KNJIGA from korisnik right join iznajmljivanje on iznajmljivanje.ID_KORISNIK = korisnik.ID_KORISNIK group by korisnik.ID_KORISNIK;

2. Prikazati ukupan broj biblioteka za svaku opštinu. Rezultate sortirati opadajući.

select count(id_biblioteka) as broj_biblioteka, opstina.ID_OPSTINA, opstina.ID_REGION, opstina.NAZIV from biblioteka right join opstina on biblioteka.ID_OPSTINA = opstina.ID_OPSTINA group by opstina.ID_OPSTINA order by broj_biblioteka desc;

3. Za svaku knjigu prikazati ukupan broj autora ukoliko knjiga ima više od jednog autora.

select count(pisac.ID_PISAC) as broj_pisaca,pisac.ID_PISAC, knjiga.IME, knjiga.ID_KNJIGA from pisac right join knjiga on knjiga.ID_PISAC = pisac.ID_PISAC where pisac.BROJ_PISACA > 1 group by knjiga.ID_KNJIGA;

4. Prikazati spisak svih biblioteka zajedno sa svim informacijama o korisnicima koji su iz nje 
iznajmili knjige

select biblioteka.IME as spisak_biblioteka, korisnik.ID_KORISNIK, korisnik.KORISNICKO_IME, korisnik.SIFRA, korisnik.ID_BIBLIOTEKA from biblioteka right join korisnik on korisnik.ID_BIBLIOTEKA = biblioteka.ID_BIBLIOTEKA group by korisnik.ID_KORISNIK;

5.  Prikazati sve bibliotekare zajedno sa informacijama o bibliteci u kojoj rade.

select zaposleni.ID_ZAPOSLENI, zaposleni.ID_BIBLIOTEKA, zaposleni.POZICIJA, zaposleni.IME as ime_zaposleni,biblioteka.ID_BIBLIOTEKA, biblioteka.ID_ADRESA as adresa_biblioteka,biblioteka.IME as naziv_biblioteke,biblioteka.ID_OPSTINA as biblioteka_opstina from zaposleni join biblioteka on biblioteka.ID_BIBLIOTEKA = zaposleni.ID_BIBLIOTEKA where POZICIJA = 'bibliotekar';

6. Prikazati biblioteku koja ima maksimalan broj izdatih knjiga. Ukoliko ima više takvih biblioteka 
prikazati ih sve.

select biblioteka.ID_BIBLIOTEKA, biblioteka.ID_ADRESA, biblioteka.IME, biblioteka.ID_OPSTINA,biblioteka.KAPACITET, biblioteka.BROJ_IZDANJA from biblioteka join iznajmljivanje on iznajmljivanje.ID_BIBLIOTEKA = biblioteka.ID_BIBLIOTEKA where iznajmljivanje.BROJ_IZN_KNJIGA < biblioteka.KAPACITET;

Ili ako se misli na najveci broj izdatih knjiga a ne iznamljenih, onda bi ovako izgledao upit:


select biblioteka.ID_BIBLIOTEKA, biblioteka.ID_ADRESA, biblioteka.IME, biblioteka.ID_OPSTINA, biblioteka.KAPACITET, biblioteka.BROJ_IZDANJA from biblioteka where biblioteka.BROJ_IZDANJA = (select max(biblioteka.BROJ_IZDANJA) FROM biblioteka);