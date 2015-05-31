/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.PlanoContasMegaContabil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author PEMPEC
 */
public class SincronizeMegaContabil {

    private static SincronizeMegaContabil conectMegaContabil;
    private Connection connection;

    private SincronizeMegaContabil() {
    }

    public static SincronizeMegaContabil getInstance() {

        if (conectMegaContabil == null) {
            conectMegaContabil = new SincronizeMegaContabil();
        }

        return conectMegaContabil;

    }

    private Connection getConnection() throws SystemException {

        if (connection == null) {

            try {

                String driver = ResourcePropertiesLocator.getMessage("driver_class_mega_contabil");
                String user = ResourcePropertiesLocator.getMessage("username_mega_contabil");
                String pass = ResourcePropertiesLocator.getMessage("password_mega_contabil");
                String url = ResourcePropertiesLocator.getMessage("url_mega_contabil");

                Class.forName(driver);

                connection = DriverManager.getConnection(url, user, pass);

            } catch (Exception e) {
                throw new SystemException(e.getMessage());
            }
        }

        return connection;
    }

    public Collection<PlanoContasMegaContabil> getPlanosContas() throws SystemException {

        Collection<PlanoContasMegaContabil> retorno = null;

        try {

            StringBuilder builder = new StringBuilder();

            builder.append(" SELECT ID_CPLANOEMPRESA, NMCONTA, CONTA, DGVER, CODREDUZ, DGREDUZ, INSCMF, ");
            builder.append(" TIPO, GRAU, ORDEM_DIPJ, RELACIONAMENTO, NATUREZA ");
            builder.append(" FROM CPLANOEMPRESA WHERE LEI11638 = -1 AND GRAU = 5  AND EMPRESA = 1");

            //TEM QUE SER EMPRESA 1 PORQUE O MEGA DEIXA COLOCAR VARIAS EMPRESAS E A MESMA CONTA E CODREDUZ


            PreparedStatement statement = this.getConnection().prepareStatement(builder.toString());

            ResultSet resultSet = statement.executeQuery();

            retorno = new ArrayList<PlanoContasMegaContabil>();

            while (resultSet.next()) {

                PlanoContasMegaContabil planoContas = new PlanoContasMegaContabil();

                planoContas.setId(resultSet.getString(1));
                planoContas.setDescricao(resultSet.getString(2));
                planoContas.setConta(resultSet.getString(3));
                planoContas.setDigitoConta(resultSet.getString(4));
                planoContas.setContaReduzida(resultSet.getString(5));
                planoContas.setDigitoContaReduzida(resultSet.getString(6));
                planoContas.setInscricaoCMF(resultSet.getString(7));
                planoContas.setTipo(resultSet.getString(8));
                planoContas.setGrau(resultSet.getString(9));
                planoContas.setOrdemDIPJ(resultSet.getInt(10));
                planoContas.setRelacionamento(resultSet.getInt(11));
                planoContas.setNatureza(resultSet.getInt(12));

                retorno.add(planoContas);

            }


        } catch (SQLException ex) {
            throw new SystemException(ex.getMessage());
        }

        return retorno;

    }
}
