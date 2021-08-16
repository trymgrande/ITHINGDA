import java.io.*;
class DataValidator {
    static double volumeConstant = (4.0 / 3) * (Math.PI);
    double avogadros = 6.022 * Math.pow(10, 23);

    public void calculateDensityPerAtom(String elementName, double atomicWeight, double atomicSizeEmp, double atomicSizeCalc,
                                        double tableDensity) {
        // some constants and formulas

        // atomic radius is in picometers: 1.0 * 10^-12 meters.
        // atomic weight is in u: g/mol

        System.out.println("Element: " + elementName);

        // mass per atom in g
        double gramsPerAtom = atomicWeight / avogadros;
        System.out.println("Weight of single atom: " + gramsPerAtom);

        // volume per atom in cm/3 from empirical value
        double realRadius = atomicSizeEmp * Math.pow(10, -12);
        System.out.println("Radius in meters: " + realRadius);
        double radiusVariable = Math.pow(realRadius, 3);
        double atomicVolume = 100 * (volumeConstant * radiusVariable);

        // density from empirical radii
        double densityPerAtom = gramsPerAtom / atomicVolume;
        System.out.println("Density of single atom with empirical radii: " + densityPerAtom + " g/cm^3");

        // volume per atom in cm/3 from calculated value
        realRadius = atomicSizeCalc * Math.pow(10, -12);
        System.out.println("Radius in meters: " + realRadius);
        radiusVariable = Math.pow(realRadius, 3);
        atomicVolume = 100 * (volumeConstant * radiusVariable);

        // density from calculated radii
        densityPerAtom = gramsPerAtom / atomicVolume;
        System.out.println("Density of single atom with calculated radii: " + densityPerAtom + " g/cm^3");

        //We notice that the density per atom is much higher than the table values, this is due to
        //the formula being atomic weight / atomic volume, whereas the table contains elements in room temperature
        //at standard pressure, allowing us to speculate that the atoms are much more spread out, creating much less
        //atomic weight per volume of space than within a single atom. The data, as seen below, supports this theory.

        //For part 2, we know that the table density is given in the parameter, we must calculate the amount of space
        //between atoms to recreate this density with the density formulas we know.

        //we know that from the parameter, 1cm^3 cube will weigh + tableDensity + grams.
        double weightCube = tableDensity;
        //we find number of lead atoms by taking the mass of the cube and dividing by weight per atom.
        double numOfAtoms = weightCube/gramsPerAtom;
        //We find number of atoms along edges by calculating (numOfAtoms)^(1/3)
        double edgeAtoms = Math.cbrt(numOfAtoms);
        //We find distance by taking 1cm divided by edgeAtoms
        double distance = 1/edgeAtoms;
        System.out.println("Distance between atoms to recreate table density = " + distance + " cm.");
    }

    public static void main(String[] args) {
        DataValidator dV = new DataValidator();
        //test without file
        dV.calculateDensityPerAtom("Hydrogen",1.00784, 25, 53, 0.00008988);
        File file = new File("C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 4\\kjemi\\øvinger\\øving1\\density_calculator\\src\\vekt.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                System.out.println(st);
                String[] data = st.split(" ");
                dV.calculateDensityPerAtom(data[0], Double.parseDouble(data[3]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[4]));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
