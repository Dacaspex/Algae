# Algae
![fractal header image](https://github.com/Dacaspex/Algae/blob/master/.github/images/export-2020-06-08%20-%2022-30-23-crop.png)

> A fractal image exploration and generator for the Julia Set, Mandelbrot Set and Burning Ship fractal.

This program is capable of generating fractal images the three mentioned fractal types. Various colouring algorithms
have been implemented to obtain beautiful wallpapers/icons/emblems/etc. The images can be exported to any resolution.
For a programmer, the internal API allows for easy addition of new fractal types and colouring algorithms. 

## Motivation
This project was my first take with a bigger Java program, asynchronous code and building an end-to-end application
with reusable components. The idea behind the program was to have a balance between control and the randomness of
fractals to generate stunning images to use as wallpapers/icons/etc.

## Examples
<table>
  <tr>
    <td valign="top"><img src="https://github.com/Dacaspex/Algae/blob/master/.github/images/export-2020-06-08--22-48-08.png"></td>
    <td valign="top"><img src="https://github.com/Dacaspex/Algae/blob/master/.github/images/export-2020-06-08--22-42-24.png"></td>
  </tr>
  <tr>
    <td valign="top"><img src="https://github.com/Dacaspex/Algae/blob/master/.github/images/export-2020-06-08--22-50-01.png"></td>
    <td valign="top"><img src="https://github.com/Dacaspex/Algae/blob/master/.github/images/export-2020-06-08--22-54-49.png"></td>
  </tr>
</table>

## Installation instructions
This program uses Maven to compile and requires one external dependency, namely [PropertySheet](https://github.com/Dacaspex/PropertySheet). 
In IntelliJ, you can simply add the compiled jar as a project dependency in the project structure settings. 
