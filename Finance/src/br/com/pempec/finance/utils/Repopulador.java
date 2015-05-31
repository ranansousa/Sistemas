/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.view.TelaPrincipal;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author PEMPEC
 */
public class Repopulador {

    public static void repopulador(Tela tela, Object object) {

        JDesktopPane desktop = TelaPrincipal.desktopPane;

        JInternalFrame[] internalFrames = desktop.getAllFrames();

        for (JInternalFrame jInternalFrame : internalFrames) {

            if (jInternalFrame != null && !jInternalFrame.isClosed()
                    && jInternalFrame.isVisible() && jInternalFrame.isShowing() && jInternalFrame instanceof IRepopuladorNew) {

                ((IRepopuladorNew) jInternalFrame).repopularCombos(tela, object);

            }

        }

    }

    public static void repopulador() {

        JDesktopPane desktop = TelaPrincipal.desktopPane;

        JInternalFrame[] internalFrames = desktop.getAllFrames();

        for (JInternalFrame jInternalFrame : internalFrames) {

            if (jInternalFrame != null && !jInternalFrame.isClosed()
                    && jInternalFrame.isVisible() && jInternalFrame.isShowing() && jInternalFrame instanceof IRepopulador) {

                ((IRepopulador) jInternalFrame).repopularCombos();

            }

        }

    }
}
