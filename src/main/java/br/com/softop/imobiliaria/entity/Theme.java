/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "theme")
public class Theme {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "header_background")
    private String headerBackground;
    @Column(name = "header_background_hover")
    private String headerBackgroundHover;
    @Column(name = "header_text_color")
    private String headerTextColor;
    @Column(name = "footer_background")
    private String footerBackground;
    @Column(name = "footer_border_color")
    private String footerBorderColor;
    @Column(name = "footer_text_color")
    private String footerTextColor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(String headerBackground) {
        this.headerBackground = headerBackground;
    }

    public String getHeaderBackgroundHover() {
        return headerBackgroundHover;
    }

    public void setHeaderBackgroundHover(String headerBackgroundHover) {
        this.headerBackgroundHover = headerBackgroundHover;
    }

    public String getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(String headerTextColor) {
        this.headerTextColor = headerTextColor;
    }

    public String getFooterBackground() {
        return footerBackground;
    }

    public void setFooterBackground(String footerBackground) {
        this.footerBackground = footerBackground;
    }

    public String getFooterBorderColor() {
        return footerBorderColor;
    }

    public void setFooterBorderColor(String footerBorderColor) {
        this.footerBorderColor = footerBorderColor;
    }

    public String getFooterTextColor() {
        return footerTextColor;
    }

    public void setFooterTextColor(String footerTextColor) {
        this.footerTextColor = footerTextColor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Theme other = (Theme) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

     
    
    
}
