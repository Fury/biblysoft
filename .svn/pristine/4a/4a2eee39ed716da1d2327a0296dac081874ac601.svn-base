package org.bibly.logic.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.models.Boek;
import org.bibly.logic.models.ItemExemplaar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class ExcelImporter {

    public void ImportExcel(String location, IBoekDAO boekDAO, IExemplaarDAO exemplaarDAO) {
        FileInputStream inp = null;
        try {
            inp = new FileInputStream(location);

            Workbook wb = WorkbookFactory.create(inp);
            List<Boek> boeken = new ArrayList<Boek>();
            List<ItemExemplaar> exemplaren = new ArrayList<ItemExemplaar>();

            for (int i = 0; i < 1; i++) {
                Sheet sheet = wb.getSheetAt(i);

                int kenmerkCol = 0;
                int titelCol = 1;
                int auteurCol = 2;
                int uitgavePlaatsCol = 3;
                int uitgeverCol = 4;
                int jaarCol = 5;

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        for (Cell cell : row) {
                            String s = cell.getRichStringCellValue().toString();
                            if (s.equals("Plaatskenmerk")) {
                                kenmerkCol = cell.getColumnIndex();

                            } else if (s.equals("Titel")) {
                                titelCol = cell.getColumnIndex();

                            } else if (s.equals("Auteur")) {
                                auteurCol = cell.getColumnIndex();

                            } else if (s.equals("Plaats van uitgave")) {
                                uitgavePlaatsCol = cell.getColumnIndex();

                            } else if (s.equals("Uitgever")) {
                                uitgeverCol = cell.getColumnIndex();

                            } else if (s.equals("Jaar")) {
                                jaarCol = cell.getColumnIndex();

                            }
                        }
                    } else {
                        Boek b = new Boek();
                        ItemExemplaar exemplaar = new ItemExemplaar();
                        for (Cell cell : row) {
                            if (cell.getColumnIndex() == kenmerkCol) {
                                exemplaar.setPlaatsKenmerk(cell
                                        .getRichStringCellValue().toString());
                            } else if (cell.getColumnIndex() == titelCol) {
                                b.setTitel(cell.getRichStringCellValue()
                                        .toString());
                            } else if (cell.getColumnIndex() == auteurCol) {
                                b.setAuteur(cell.getRichStringCellValue()
                                        .toString());
                            } else {
                                if (cell.getColumnIndex() == uitgeverCol) {
                                    if (cell.getCellType() == CELL_TYPE_STRING) {
                                        b.setUitgever(cell.getStringCellValue());
                                    }
                                }
                            }

                            if (cell.getColumnIndex() == jaarCol) {
                                if (cell.getCellType() == CELL_TYPE_NUMERIC) {
                                    int x = (int) cell.getNumericCellValue();
                                    b.setUitgaveJaar(valueOf(x));
                                }
                            }

                        }
                        if ((b != null) && (b.getTitel() != null)) {
                            List<Boek> boekenlist = boekDAO.findByTitel(b.getTitel());
                            if (boekenlist.size() == 0) {
                                if (!ZoekInBoekenLijst(boeken, exemplaren, b, exemplaar)) {
                                    b.addExemplaar(exemplaar);
                                    boeken.add(b);
                                    exemplaar.setItem(b);
                                    exemplaren.add(exemplaar);
                                }
                            } else {
                                Boek boek = boekenlist.get(0);
                                boek.addExemplaar(exemplaar);
                                exemplaar.setItem(boek);
                                boeken.add(boek);
                                exemplaren.add(exemplaar);
                            }
                        }

                    }
                }
            }

            boekDAO.save(boeken);

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Geen idee waar");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                inp.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The end");

    }

    private boolean ZoekInBoekenLijst(List<Boek> boeken, List<ItemExemplaar> exemplaren, Boek b, ItemExemplaar exemplaar) {
        try {
            for (Boek boek : boeken) {
                if (boek != null) {
                    if (boek.getTitel().equals(b.getTitel()) && boek.getAuteur().equals(boek.getAuteur())) {
                        Boek b2 = boek;
                        boeken.remove(boek);

                        b.addExemplaar(exemplaar);
                        exemplaar.setItem(b);
                        exemplaren.add(exemplaar);
                        boeken.add(b2);
                        return true;
                    }
                } else {
                    boeken.remove(boek);
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
