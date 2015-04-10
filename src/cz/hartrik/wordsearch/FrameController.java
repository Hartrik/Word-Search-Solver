package cz.hartrik.wordsearch;

import cz.hartrik.dictionary.DictionaryManager;
import cz.hartrik.wordsearch.solve.*;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

/**
 * Controller k hlavnímu oknu.
 *
 * @version 2015-04-10
 * @author Patrik Harag
 */
public class FrameController implements Initializable {
    
    @FXML private TextArea tDictionary;
    @FXML private TextArea tWordSearch;
    
    @FXML private TextField tResult;
    @FXML private WebView   wPreview;
    @FXML private ListView<String> lMatches;
    
    @FXML private ToggleGroup dictionaryGroup;
    @FXML private RadioButton rDictionaryUser;
    @FXML private RadioButton rDictionaryCzech;
    @FXML private RadioButton rDictionaryEnglish;
    
    private HtmlPreview preview;
    
    @FXML
    private void solve(ActionEvent event) {
        // vstup, validace
        WsDictionary wsd = createWsDictionary();
        
        String input = tWordSearch.getText();
        if (!isValid(input)) { clearResults(); return; }
        
        char[][] charArray = StringUtils.parseWordSearch(input);
        
        if (!isValid(charArray)) { clearResults(); return; }
        
        // výpočet
        final NumericComparator numericComparator = 
                new NumericComparator(charArray.length, charArray[0].length, wsd);
        
        new Solver(charArray, numericComparator).solve();
        
        // výstup
        int[][] resultArray = numericComparator.getResult();
        
        final String result = new Result(charArray, resultArray).createResult();
        preview = new HtmlPreview(charArray, resultArray);
        
        Platform.runLater(new Runnable() {
            @Override public void run() {
                tResult.setText(replaceReverse(result).toUpperCase());
                wPreview.getEngine().loadContent(preview.createPreview());
                lMatches.getItems().setAll(numericComparator.getMatches());
            }
        });
    }
    
    private WsDictionary createWsDictionary() {
        if (rDictionaryUser.isSelected()) {
            String[] array = StringUtils.parseDictionary(tDictionary.getText());
            Set<String> dictInSet = new LinkedHashSet<>();
            dictInSet.addAll(Arrays.asList(array));
            return new WsDictionaryUser(dictInSet);
        } else {
            DictionaryManager instance = DictionaryManager.getInstance();
            if (rDictionaryCzech.isSelected())
                return new WsDictionaryBig(instance.get("cs").getDictionary());
            else
                return new WsDictionaryBig(instance.get("en").getDictionary());
        }
    }
    
    private boolean isValid(String input) {
        input = input.trim();
        return (input.length() >= 9);
    }
    
    private boolean isValid(char[][] input) {
        return ((input.length >= 3) || (input[0].length >= 3));
    }
    
    public void clearResults() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                preview = null;
                tResult.setText("");
                wPreview.getEngine().loadContent(HtmlPreview.startDocument
                        + HtmlPreview.endDocument);
                lMatches.getItems().setAll(new ArrayList<String>());
            }
        });
    }
    
    private String replaceCH(String string) {
        return string.replaceAll("(?i)ch", "#");
    }
    
    private String replaceReverse(String string) {
        return string.replace("#", "CH");
    }
    
    // --- inicializace
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearResults();
        initDictionaryListener();
        initList();
    }
    
    private void initDictionaryListener() {
        dictionaryGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {

            @Override public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle oldToggle, Toggle newToggle) {
                tDictionary.setDisable(!rDictionaryUser.isSelected());
            }
        });
    }
    
    private void initList() {
        lMatches.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldString, final String newString) {
                if (newString == null) return;
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        wPreview.getEngine().loadContent(
                                preview.createPreview(newString));
                    }
                });
            }
        });
    }
    
    @FXML private void clear1() {
        String[] lines = StringUtils.parseDictionary(tDictionary.getText());
        tDictionary.setText(StringUtils.fold(lines, ", "));
    }
    
    @FXML private void clear2() {
        String[] lines = StringUtils.clearWordSearch(tWordSearch.getText());
        tWordSearch.setText(StringUtils.fold(lines, "\n").toUpperCase());
    }
    
    @FXML private void delete1() { tDictionary.setText(""); }
    @FXML private void delete2() { tWordSearch.setText(""); }
    
    @FXML private void replaceCH1() { replaceCH(tDictionary); };
    @FXML private void replaceCH2() { replaceCH(tWordSearch); };
    
    private void replaceCH(TextArea textArea) {
        String text = textArea.getText();
        String replaceCH = replaceCH(text);
        textArea.setText(replaceCH);
    }
    
}