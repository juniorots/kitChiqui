/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kitchiqui.framework.persistence;

import org.joda.time.LocalDateTime;

/**
 *
 * @author Rafael Quintino
 */
public interface IHaveLastUpdate {

    void setLastUpdate(LocalDateTime localDateTime);

    LocalDateTime getLastUpdate();
}
