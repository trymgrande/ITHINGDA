/**
 * Datamodeller.java - "Programmering i Java", 4.utgave - 2012-02-29
 *
 * Filen inneholder datamodellklasser som kommuniserer med
 * oppussingsprosjektobjektet vårt. Et objekt av en slik klasse danner
 * ei bru mellom GUI-komponenten og oppussingsprosjektobjektet.
 * Oppussingsprosjektobjektet er argument til konstruktøren.
 */

/**
 * Datamodellen som ligger bak JList-boksen til høyre i vinduet på figur 19.11.
 * Interfacet ListModel setter betingelsene for datamodellen bak JList.
 * Klassen AbstractListModel implementerer alle metodene unntatt
 * getSize() og getElementAt(). I tillegg trenger vi å lage en metode
 * som sørger for å varsle at det er skjedd endringer i oppussingsprosjektobjektet.
 */

import java.util.Formatter;
import javax.swing.*;
import javax.swing.table.*;
import oppussingsprosjekt.*;

class EgenListeModell extends AbstractListModel<Materiale> {
  private Oppussingsprosjekt prosjektet;

  public EgenListeModell(Oppussingsprosjekt prosjektet) {
    this.prosjektet = prosjektet;
  }

  public int getSize() {
    return prosjektet.finnAntMaterialer();
  }

  public Materiale getElementAt(int indeks) {
    return prosjektet.finnMateriale(indeks);
  }

  public void registrerNyttElement(Object kilde, int indeks) {
    fireIntervalAdded(kilde, indeks, indeks); // oppdaterer listen på skjermen
  }
}

/*
 * Datamodellen som ligger bak JTable-komponenten på figur 19.11.
 * Betingelsene som JTable setter, er gitt av interfacet TableModel.
 * Klassen AbstractTableModel implementerer alle metodene unntatt
 * getColumnCount(), getRowCount() og getValueAt(). Disse må vi implementere i
 * vår subklasse. I tillegg må vi sørge for at JTable-komponenten får tilgang
 * til riktige kolonnenavn ved å lage getColumnName(). Endelig begrenser vi
 * brukerens muligheter ved å lage vår egen isCellEditable()
 */
class EgenTabellModell extends AbstractTableModel {
  private Oppussingsprosjekt prosjektet;

  public EgenTabellModell(Oppussingsprosjekt prosjektet) {
    this.prosjektet = prosjektet;
  }

  public String getColumnName(int indeks) {
    return (indeks >= 0 && indeks < Konstanter.KOL_NAVN.length)
      ? Konstanter.KOL_NAVN[indeks]
      : "";
  }

  public boolean isCellEditable(int rad, int kolonne) {
    return false;
  }

  public int getRowCount() {
    return (prosjektet == null) ? 0 : prosjektet.finnAntFlater();
  }

  public int getColumnCount() {
    return 7;
  }

  public Object getValueAt(int rad, int kolonne) {
    Flate flaten = prosjektet.finnFlate(rad);
    Materiale materialet = flaten.getMaterialtype();
    Formatter utformat = new Formatter();
    switch (kolonne) {
    case 0:
      return flaten.getNavn();
    case 1:
      return utformat.format(Konstanter.FORMAT, flaten.getBredde());
    case 2:
      return utformat.format(Konstanter.FORMAT, flaten.getLengde());
    case 3:
      if (materialet != null) {
        String navn = materialet.getClass().getName(); // klassenavn inkl. pakkenavn
        int pos = navn.lastIndexOf('.');
        if (pos >= 0) {
          navn = navn.substring(pos + 1, navn.length());  // bare klassenavnet
        }
        return navn + ": " + materialet.getNavn();
      } else {
        return null;
      }
    case 4:
      return (materialet != null)
                ? utformat.format( Konstanter.FORMAT,
                   materialet.beregnMaterialbehov(flaten))
                : null;
    case 5:
      return (materialet != null)
                 ? utformat.format(Konstanter.FORMAT, materialet.getPris())
                 : null;
    case 6:
      return (materialet != null)
                 ? utformat.format(Konstanter.FORMAT, materialet.beregnTotalpris(flaten))
                 : null;
    default:
      return null;
    }
  }
}
