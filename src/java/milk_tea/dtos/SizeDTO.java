/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.dtos;

/**
 *
 * @author Loi Nguyen
 */
public class SizeDTO {
    private int sizeID;
    private String sizeName;
    private String status;

    public SizeDTO(int sizeID, String sizeName) {
        this.sizeID = sizeID;
        this.sizeName = sizeName;
    }

    public SizeDTO(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
