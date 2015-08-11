package atux.common;

import java.util.*;


public class AtuxTableComparator implements Comparator {

  /** Almacena columna para sorteo */
  protected int m_sortCol;

  /** Almacena modo de sorteo */
  protected boolean m_sortAsc;


  public AtuxTableComparator(int sortCol, boolean sortAsc) {

    m_sortCol = sortCol;
    m_sortAsc = sortAsc;

  }

  public int compare(Object o1, Object o2) {

    ArrayList arrayList1 = (ArrayList)o1;
    ArrayList arrayList2 = (ArrayList)o2;

    int result = arrayList1.get(m_sortCol).toString().toLowerCase().compareTo(arrayList2.get(m_sortCol).toString().toLowerCase());

    if (!m_sortAsc) result = -result;

    return result;

  }

}

