abstract class Instrument {
    private String name;
    protected int year;

    public Instrument(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public abstract String play();

    public String getInstrumentDetails() {
        return "Instrument: " + name + ", Year: " + year;
    }

    public String getName() {
        return name;
    }
}

interface Tunable {
    String tune();
    String adjustPitch(boolean up);
}

interface Maintainable {
    String clean();
    String inspect();
}

class StringedInstrument extends Instrument {
    private int numberOfStrings;

    public StringedInstrument(String name, int year, int numberOfStrings) {
        super(name, year);
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public String play() {
        return "Playing the stringed instrument";
    }

    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Strings: " + numberOfStrings;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }
}

class Guitar extends StringedInstrument implements Tunable, Maintainable {
    private String guitarType;

    public Guitar(String name, int year, int numberOfStrings, String guitarType) {
        super(name, year, numberOfStrings);
        this.guitarType = guitarType;
    }

    @Override
    public String play() {
        return "Playing the " + guitarType + " guitar with " + getNumberOfStrings() + " strings";
    }

    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Type: " + guitarType;
    }

    @Override
    public String tune() {
        return "Tuning the " + guitarType + " guitar";
    }

    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of the guitar" : "Decreasing pitch of the guitar";
    }

    @Override
    public String clean() {
        return "Cleaning the " + guitarType + " guitar";
    }

    @Override
    public String inspect() {
        return "Inspecting the " + guitarType + " guitar from " + year;
    }
}

class Piano extends Instrument implements Tunable, Maintainable {
    private boolean isGrand;

    public Piano(String name, int year, boolean isGrand) {
        super(name, year);
        this.isGrand = isGrand;
    }

    @Override
    public String play() {
        return "Playing the " + (isGrand ? "grand" : "upright") + " piano";
    }

    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Type: " + (isGrand ? "Grand" : "Upright");
    }

    @Override
    public String tune() {
        return "Tuning the piano";
    }

    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of the piano" : "Decreasing pitch of the piano";
    }

    @Override
    public String clean() {
        return "Cleaning the piano keys and interior";
    }

    @Override
    public String inspect() {
        return "Inspecting the " + (isGrand ? "grand" : "upright") + " piano from " + year;
    }
}

public class MusicShop {
    public static void main(String[] args) {
        Instrument[] instruments = new Instrument[3];
        instruments[0] = new Guitar("Fender Stratocaster", 2020, 6, "electric");
        instruments[1] = new Piano("Steinway", 2015, true);
        instruments[2] = new Guitar("Martin", 2018, 12, "acoustic");

        System.out.println("===== PLAYING INSTRUMENTS =====");
        for (Instrument instrument : instruments) {
            System.out.println(instrument.play());
            System.out.println(instrument.getInstrumentDetails());
        }

        System.out.println("\n===== MAINTAINING INSTRUMENTS =====");
        for (Instrument instrument : instruments) {
            System.out.println("Working with: " + instrument.getName());

            if (instrument instanceof Tunable) {
                Tunable t = (Tunable) instrument;
                System.out.println(t.tune());
                System.out.println(t.adjustPitch(true));
            }

            if (instrument instanceof Maintainable) {
                Maintainable m = (Maintainable) instrument;
                System.out.println(m.clean());
                System.out.println(m.inspect());
            }

            System.out.println("-----");
        }
    }
}
