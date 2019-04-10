package com.zafar.quizit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zafar.quizit.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz-It.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){

        if(instance==null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE "+
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CategoriesTable.COLUMN_NAME + " TEXT UNIQUE " + ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION+ " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Science");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
        Category c4 = new Category("Superheroes");
        insertCategory(c4);


    }

    //Add one category at a time
    public void addCategory(Category category){
        db = getWritableDatabase();
        insertCategory(category);
    }

    //Add a list of categories
    public void addCategories(List<Category> categories){
        db = getWritableDatabase();

        for (Category category: categories){
            insertCategory(category);
        }
    }

    private void insertCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }
    private void fillQuestionsTable() {
        Question q = new Question("Programming, Easy: What language is used to style websites?",
                "HTML", "C", "CSS", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Easy: What is binary?",
                "0 and 1s", "A and Bs", "X and Zs", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Easy: Which of the following is a keyword for a loop",
                "Looping", "While", "Go", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Easy: What is an object in Java",
                "An instance of a class", "A thing", "A method", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Easy: What is x++",
                "Add 2 to x", "Add x to x", "Add 1 to x", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Easy: What is 10%2",
                "5", "0", "2", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q);



        q = new Question("Programming, Medium: What is a primary key?",
                "It identifies the database by a value", "The first key", "A key in another table", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Medium: Why is a semaphore used in SQL for Java?",
                "As a loop", "Locking", "Opening", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Medium: What is the smallest type of memory?",
                "Physical", "Cloud", "Cache", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Medium: How does MongoDB store data?",
                "Tables", "Documents", "Cloud", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Medium: What is union in SQL?",
                "Same attributes, putting data from 2 select statements together", "Adding values in 2 tables numerically", "Different attributes, putting data from 2 select statements together", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Medium: What language is this app coded in?",
                "C", "Python", "Java", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q);



        q = new Question("Programming, Hard: What is the complexity of a single loop?",
                "O(n)", "O(n^2)", "O(n/2)", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Hard: Which of the following is a blockchain platform?",
                "Multichain", "Softchain", "Cryptochain", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Hard: What is binary for 16?",
                "010", "1000", "10000", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Hard: What connection type is generally used for data streaming?",
                "TCP", "UDP", "DDP", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Hard: What is the difference between margin and padding?",
                "They are the same", "Padding is spacing within an element while margin is outside", "Padding is spacing outside an element while margin is inside", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);
        q = new Question("Programming, Hard: How many nodes does a complete 3 level binary tree have?",
                "3", "5", "7", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q);




        q = new Question("Science, Easy: What are the structures that orbit a nucleus?",
                "Proton", "Atoms", "Electrons", 3,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Easy: Which of the following is a gas?",
                "FE", "C02", "HCL", 2,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Easy: What is the biggest planet in our solar system?",
                "Jupiter", "Uranus", "Earth", 1,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Easy: Is HCL an acid or base?",
                "Base", "Neither", "Acid", 3,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        insertQuestion(q);
         q = new Question("Science, Easy: What is the element with atomic number of 6?",
                "Oxygen", "Carbon", "Hydrogen", 2,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        insertQuestion(q);


        q = new Question("Science, Medium: Penicillin is used to fight what type of infections?",
                "Bacterial", "Respiratory", "Ear", 1,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Medium: When light bends as it enters a different medium the process is known as what?",
                "Reflection", "Refraction", "Diffraction", 2,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Medium: A magnifying glass is what type of lens?",
                "Concave", "Convex", "Contralateral", 2,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Medium: Which of the following colours have the highest wavelength",
                "Red", "Blue", "Orange", 1,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        insertQuestion(q);

        q = new Question("Science, Medium: What is the mitochondria",
                "The green in a cell ", "The smallest thing in a cell", "The powerhouse of the cell", 3,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        insertQuestion(q);



        q = new Question("Science, Hard: Which of the follwing is the correct formula?",
                "f=ma", "f=m/a", "f=a/m", 1,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Hard: What is the absolute value of the force of gravity?",
                "-9.8", "9.8", "no set value", 2,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Hard: Which has more volume, a pound of muscle or a pound of fat",
                "Same volume", "Muscle", "Fat", 3,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        insertQuestion(q);
        q = new Question("Science, Hard: Do we only use 10% of our brain?",
                "Yes", "No", "Depends on the person", 1,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        insertQuestion(q);

        q = new Question("Science, Hard: How many atoms are in a mole",
                "6.022x10^21", "6.022x10^22", "6.022x10^23", 3,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        insertQuestion(q);



        q = new Question("Math, Easy: What is 5*6?",
                "40", "70", "30", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Easy: What is 2+7?",
                "9", "8", "7", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Easy: What is the square root of 9?",
                "9", "3", "2", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Easy: What is an acute angle?",
                "A good looking angle", "An angle larger than 90 degrees", "An angle smaller than 90 degrees", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Easy: What is the correct order of operations?",
                "BEDMAS", "MASBED", "SAMBED", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q);



        q = new Question("Math, Medium: What is the derivative of e^x?",
                "1", "e^x", "x^e", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Medium: What is the derivative of lnx?",
                "1", "1/x", "x", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Medium: What is the meaning of the derivative?",
                "Length", "Volume", "Area under graph", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Medium: What is the equation y=x^2 represent?",
                "Equation of parabola", "Equation of a line", "Equation of cubic", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Medium: What is sin(0)?",
                "0", "1", "90", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q);

        q = new Question("Math, Hard: One day, a person went to a horse racing area. Instead of counting the number of humans and horses, he counted 74 heads and 196 legs. How many humans and horses were there?",
                "37 humans and 98 horses", "31 horses and 74 humans", "24 humans and 50 horses", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Hard: What is the derivative of 6x^3 -9x + 4?",
                "6x^4 -9x^2 +C", "18x^2 -9", "(6/4)x^4 -(9/2)x^2 +4x", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Hard: A bat and a ball cost $1.10 in total. The bat costs $1.00 more than the ball. How much does the ball cost?",
                "10 cents", "A dollar", "5 cents", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Hard: What is the integral of e^(3x)?",
                "e^(3x)", "3e^(3x)", "1/3(e^(3x))", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q);
        q = new Question("Math, Hard: What is the sum of the first 10 odd numbers?",
                "100", "50", "80", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q);



        q = new Question("Superheroes, Easy: What is the Super Villain with half his face burnt?",
                "Double-Trouble", "Two-Face", "Split-face", 2,
                Question.DIFFICULTY_EASY, Category.SUPERHEROES);
        insertQuestion(q);

        q = new Question("Superheroes, Easy: What is Batman's first name?",
                "Bart", "Bruce", "Bat", 2,
                Question.DIFFICULTY_EASY, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Easy: What is the superhero who wear a tiara?",
                "Ant-man", "Spider Gwen", "Wonder Woman", 3,
                Question.DIFFICULTY_EASY, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Easy: What is Spiderman's Aunts name?",
                "May", "Maya", "Beth", 1,
                Question.DIFFICULTY_EASY, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Easy: What color is Barry Allen's (The Flash) suit?",
                "Yellow", "Black", "Red", 3,
                Question.DIFFICULTY_EASY, Category.SUPERHEROES);
        insertQuestion(q);



        q = new Question("Superheroes, Medium: Who was the first Avenger?",
                "Captain Marvel", "Captain America", "Captain Marvel", 2,
                Question.DIFFICULTY_MEDIUM, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Medium: What does Superman get his power from?",
                "Himself", "The Earth", "The Sun", 1,
                Question.DIFFICULTY_MEDIUM, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Medium: What does Green Lantern wear to use his powers?",
                "A robe", "A belt", "A ring", 3,
                Question.DIFFICULTY_MEDIUM, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Medium: Where is the Arc Reactor located on Iron Man?",
                "His chest", "His arm", "His leg", 3,
                Question.DIFFICULTY_MEDIUM, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Medium: Where is Wakanda located?",
                "South America", "Africa", "Australia", 2,
                Question.DIFFICULTY_MEDIUM, Category.SUPERHEROES);
        insertQuestion(q);


        q = new Question("Superheroes, Hard: What is the name of Thor's original hammer?",
                "Billy", "Mijinerr", "Mjolnir", 3,
                Question.DIFFICULTY_HARD, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Hard: What is Captain America's Shield made out of in the movies?",
                "Adamantium", "Iron", "Vibranium", 3,
                Question.DIFFICULTY_HARD, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Hard: Which of the following is NOT one of the 6 infinity stones?",
                "Time Stone", "Earth Stone", "Soul Stone", 2,
                Question.DIFFICULTY_HARD, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Hard: Which speedster is known as Kid Flash?",
                "Wally West", "Barry Allen", "Jessie Quick", 1,
                Question.DIFFICULTY_HARD, Category.SUPERHEROES);
        insertQuestion(q);
        q = new Question("Superheroes, Hard: What colour was Thanos before he was purple?",
                "Blue", "Grey", "He was always purple", 1,
                Question.DIFFICULTY_HARD, Category.SUPERHEROES);
        insertQuestion(q);


    }

    //Add one question
    public void addQuestion(Question question){
        db = getWritableDatabase();
        insertQuestion(question);
    }


    //Add multiple questions
    public void addQuestions(List<Question> questions){
        db = getWritableDatabase();

        for (Question question: questions){
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }



    public ArrayList<Question> getQuestions(int categoryID, String diff) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " + " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), diff};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
                } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}