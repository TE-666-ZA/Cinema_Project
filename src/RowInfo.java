class RowInfo {

  private int rowNumber;
  private Character[] rowSeats;
  private int occupiedSeats;

  public RowInfo(int rowNumber, Character[] rowSeats, int occupiedSeats) {
    this.rowNumber = rowNumber;
    this.rowSeats = rowSeats;
    this.occupiedSeats = occupiedSeats;
  }

  public int getRowNumber() {
    return rowNumber;
  }

  public Character[] getRowSeats() {
    return rowSeats;
  }

  public int getOccupiedSeats() {
    return occupiedSeats;
  }
}
