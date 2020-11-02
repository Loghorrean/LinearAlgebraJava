package com.loghorrean.algebra;

import java.util.Scanner;

public class Matrix {
    private int numRows;
    private int numColumns;
    private double[][] matrix;

    public Matrix(int length, int width) {
        this(length, width, new double[length][width]);
    }

    public Matrix(double[][] matrix) {
        this(matrix.length, matrix[0].length, matrix);
    }

    public Matrix(int length, int width, double[][] matrix) {
        this.numRows = length;
        this.numColumns = width;
        this.matrix = matrix;
    }

    public Matrix(Matrix a) {
        this.numRows = a.getNumberOfRows();
        this.numColumns = a.getNumberOfColumns();
        this.matrix = ArrayCopier.copySecondDimension(a.matrix);
    }

    public int getNumberOfRows() {
        return this.matrix.length;
    }

    public int getNumberOfColumns() {
        return this.matrix[0].length;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                builder.append("|").append(this.matrix[i][j]).append("|");
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    // Проверка матрицы на квадратность (равны длины строк и столбцов)
    public Boolean isMatrixSquare() {
        return this.matrix.length == this.matrix[0].length;
    }

    // проверка матрицы на едиичность (главная диагональ из единиц, остальное нули)
    public Boolean isMatrixSingle() {
       for (int i = 0; i < this.numRows; ++i) {
           for (int j = 0; j < this.numColumns; ++j) {
               if (i != j && this.matrix[i][j] != 0) {
                   return false;
               }
           }
       }
       return this.multiplyDiameter() != 1;
    }

    // ввод значений матрицы
    public void insertMatrixValues() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                System.out.println("Enter the value of (" + (i + 1) + "," + (j + 1) + ") element");
                this.matrix[i][j] = Integer.parseInt(in.nextLine());
            }
        }
        in.close();
    }

    // возвращает true, если матрица - нижняя треугольная
    public boolean isLowerTriangular() {
        if (this.numRows < 2) {
            return false;
        }
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < i; ++j) {
                if (this.matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // возвращает true, если матрица - верхняя треугольная
    public boolean isUpperTriangular() {
        if (this.numRows < 2) {
            return false;
        }
        for (int j = 0; j < this.numRows; j++) {
            for (int i = 0; i < j; ++i) {
                if (this.matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // возвращает строку по данному индексу
    public double[] getRowByIndex(int index) {
        --index;
        double[] row = new double[this.numColumns];
        for(int i = 0; i < this.numColumns; ++i) {
            row[i] = this.matrix[index][i];
        }
        return row;
    }

    //возвращает столбец по указанному индексу
    public double[] getColumnByIndex(int index) {
        --index;
        double[] column = new double[this.numRows];
        for(int i = 0; i < this.numRows; ++i) {
            column[i] = this.matrix[i][index];
        }
        return column;
    }

    public boolean rowContainsNegatives(int index) {
        double[] row = this.getRowByIndex(index);
        for(Double rowValue : row) {
            if (rowValue == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean colContainsNegatives(int index) {
        double[] col = this.getColumnByIndex(index);
        for (Double columnValue : col) {
            if (columnValue == 0) {
                return true;
            }
        }
        return false;
    }

    //приводит матрицу к нижнему треугольному виду
    public void matrixToLowerTriangular() {

    }

    // получение произведения элементов главной диагонали
    public double multiplyDiameter() {
        double diameter = 1;
        for (int i = 0; i < this.numRows; ++i) {
            diameter *= this.matrix[i][i];
        }
        return diameter;
    }

    // сложение строк матрицы (к row1 прибавляется row2)
    public void sumRows(int row1, int row2) {
        for (int i = 0; i < numColumns; i++) {
            this.matrix[row1 - 1][i] += this.matrix[row2 - 1    ][i];
        }
    }

    // сложение столбцов матрицы (к col1 прибавляется col2)
    public void sumCols(int col1, int col2) {
        for (int j = 0; j < this.numRows; ++j) {
            this.matrix[j][col1 - 1] += this.matrix[j][col2 - 1];
        }
    }

    // замена определенного элемента матрицы
    public void setElement(int index1, int index2, double replacement) {
        try {
            if (index1 <= 0 || index1 > this.numRows || index2 <= 0 || index2 > this.numColumns) {
                throw new MatrixException("Wrong index of an element");
            }
            this.matrix[index1][index2] = replacement;
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }

    // транспонирование матрицы
    public Matrix transposeMatrix() {
        double[][] temp = new double[this.numColumns][this.numRows];
        for (int i = 0; i < this.numColumns; ++i) {
            for (int j = 0; j < this.numRows; ++j) {
                temp[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix(temp);
    }

    // умножение строки на число
    public void multRowByNumber(int row, double multipllier) {
        for(int i = 0; i < numColumns; ++i) {
            this.matrix[row- 1][i] *= multipllier;
        }
    }

    // умножение столбца на число
    public void multColByNumber(int col, double multipllier) {
        for(int j = 0; j < numRows; ++j) {
            this.matrix[j][col - 1] *= multipllier;
        }
    }

    // умножение матрицы на число
    public void multMatrixByNumber(double multiplier) {
        for(int i = 0; i < numColumns; ++i) {
            this.multColByNumber(i + 1, multiplier);
        }
    }

    // замена строк row1 и row2 местами
    public void swapRows(int row1, int row2) {
        for (int i = 0; i < numColumns; ++i) {
            double temp = this.matrix[row1 - 1][i];
            this.matrix[row1 - 1][i] = this.matrix[row2 - 1][i];
            this.matrix[row2 - 1][i] = temp;
        }
    }

    // замена столбцов col1 и col2 местами
    public void swapCols(int col1, int col2) {
        for (int i = 0; i < numRows; ++i) {
            double temp = this.matrix[i][col1 - 1];
            this.matrix[i][col1 - 1] = this.matrix[i][col2 - 1];
            this.matrix[i][col2 - 1] = temp;
        }
    }

    // получение минора матрицы (квадратной) относительно данного элемента
    public Matrix getMinor(double index1, double index2) {
        try {
            if (!this.isMatrixSquare()) {
                throw new MatrixException("Matrix is not square");
            }
            index1 -= 1;
            index2 -= 1;
            if (index1 >= this.matrix.length || index1 < 0 || index2 >= this.matrix[0].length || index2 < 0) {
                throw new MatrixException("This element does not exist");
            }
            int newLen = this.numRows - 1;
            double[][] temp = new double[newLen][newLen];
            int counter1 = 0;
            for (int i = 0; i < this.numRows; ++i) {
                int counter2 = 0;
                if (i == index1) {
                    continue;
                }
                for(int j = 0; j < this.numRows; ++j) {
                    if (j == index2) {
                        continue;
                    }
                    temp[counter1][counter2] = this.matrix[i][j];
                    counter2++;
                }
                counter1++;
            }
            return new Matrix(newLen, newLen, temp);
        } catch(MatrixException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Прибавляет к текущей матрице строку сверху или снизу по выбору пользователя
    public void addRow(double[] row, Direction direction) throws MatrixException {
            if (row.length != this.numColumns) {
                throw new MatrixException("Row length does not match the number of columns in the matrix");
            }
            double[][] newMatrix = new double[this.numRows + 1][this.numColumns];
            int i, j;
            if (direction == Direction.BOTTOM) {
                for (i = 0; i < this.numRows; ++i) {
                    for(j = 0; j < this.numColumns; ++j) {
                        newMatrix[i][j] = this.matrix[i][j];
                    }
                }
                for (i = 0, j = newMatrix.length - 1; i < this.numColumns; ++i) {
                    newMatrix[j][i] = row[i];
                }
            }
            else if (direction == Direction.TOP) {
                for (i = 0, j = 0; i < this.numColumns; ++i) {
                    newMatrix[j][i] = row[i];
                }
                for (i = 0; i < this.numRows; ++i) {
                    for (j = 0; j < this.numColumns; ++j) {
                        newMatrix[i + 1][j] = this.matrix[i][j];
                    }
                }
            }
            else {
                throw new MatrixException("You can only add rows to the top or bottom, and you tried " + direction);
            }
            this.matrix = newMatrix;
            this.numColumns = this.matrix[0].length;
            this.numRows = this.matrix.length;
    }

    //Прибавляет к текущей матрице столбец слева или справа по выбору пользователя
    public void addCol(double[] col, Direction direction) throws MatrixException {
        if (col.length != this.numRows) {
            throw new MatrixException("Column length does not match the number of rows in the matrix");
        }
        double[][] matrix = new double[this.numRows][this.numColumns + 1];
        int i, j;
        if (direction == Direction.LEFT) {
            for (i = 0; i < this.numRows; ++i) {
                matrix[i][0] = col[i];
                for (j = 0; j < this.numColumns; ++j) {
                    matrix[i][j + 1] = this.matrix[i][j];
                }
            }
        } else if (direction == Direction.RIGHT) {
            for (i = 0; i < this.numRows; ++i) {
                for (j = 0; j < this.numColumns; ++j) {
                    matrix[i][j] = this.matrix[i][j];
                }
                matrix[i][matrix.length - 1] = col[i];
            }
        } else {
            throw new MatrixException("You can only add columns to the left or right, and you tried " + direction);
        }
        this.matrix = matrix;
        this.numRows = matrix.length;
        this.numColumns = matrix[0].length;
    }

    // сложение матриц (только в случае равного размера)
    public Matrix addMatrix(Matrix a) throws MatrixException{
        if (this.numRows != a.numRows) {
            throw new MatrixException("Number of rows differs");
        }
        if (this.numColumns != a.numColumns) {
            throw new MatrixException("Number of columns differs");
        }
        double[][] temp = new double[this.numRows][this.numColumns];
        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numColumns; ++j) {
                temp[i][j] = this.matrix[i][j] + a.matrix[i][j];
            }
        }
        return new Matrix(temp);
    }

    // умножение матриц друг с другом
    public Matrix multiplyMatrixes(Matrix a) throws MatrixException {
        if (this.numColumns != a.numRows) {
            throw new MatrixException("Number of columns on your matrix does not does not match the number of rows in given matrix");
        }
        double [][] temp = new double[this.numRows][a.numColumns];
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[0].length; ++j) {
                for (int k = 0; k < this.numColumns; ++k) {
                    temp[i][j] += this.matrix[i][k] * a.matrix[k][j];
                }
            }
        }
        return new Matrix(temp);
    }

    // получение обратной матрицы (чтобы не записывать дроби в матрицу, дробный множитель выводится отдельно)
    public Matrix getInverseMatrix() throws MatrixException {
        if (!this.isMatrixSquare()) {
            throw new MatrixException("Matrix is not square");
        }
        System.out.println("Multiplier is: 1/" + this.getDeterminantRecursively());
        double[][] temp = new double[this.numRows][this.numColumns];
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                int sign = 1;
                if ((i + j) % 2 != 0) {
                    sign = -1;
                }
                temp[i][j] = sign * this.getMinor(i + 1, j + 1).getDeterminantRecursively();
            }
        }
        Matrix matrix = new Matrix(temp).transposeMatrix();
        return matrix;
    }

    //TODO: threads to count determinants of the minors
    // получение определителя матрицы рекурсивно (через миноры)
    public double getDeterminantRecursively() throws MatrixException {
        if (!this.isMatrixSquare()) {
            throw new MatrixException("Matrix is not square");
        }
        if (this.numRows == 1) {
            return this.matrix[0][0];
        }
        if (this.numRows == 2) {
            return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
        }
        double localDet = 0;
        for (int j = 0; j < this.numColumns; ++j) {
            int sign = 1;
            if (j % 2 != 0) {
                sign = -1;
            }
            Matrix matrix = this.getMinor(1, j+1);
            localDet += sign * this.matrix[0][j] * matrix.getDeterminantRecursively();
        }
        return localDet;
    }
}
