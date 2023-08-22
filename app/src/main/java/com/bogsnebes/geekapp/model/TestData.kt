package com.bogsnebes.geekapp.model

import com.bogsnebes.geekapp.model.dto.FactDto
import io.reactivex.rxjava3.core.Single

object TestData {
    private val factsList = listOf(
        FactDto(
            id = "521997d834286f6e624f35f4aa193915",
            text = "In the 1980`s American migraines increased by 60%.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/521997d834286f6e624f35f4aa193915"
        ),
        FactDto(
            id = "26ce38f50c4bd0d6e36c1f011ebac02d",
            text = "Leonardo Da Vinci invented the scissors.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/26ce38f50c4bd0d6e36c1f011ebac02d"
        ),
        FactDto(
            id = "8198a291cc48ce10c45937ba9b35a395",
            text = "To Ensure Promptness, one is expected to pay beyond the value of service – hence the later abbreviation: T.I.P.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/8198a291cc48ce10c45937ba9b35a395"
        ),
        FactDto(
            id = "b8583f70c337e9723399acbf6cb05b2f",
            text = "William Marston engineered one of the earliest forms of the polygraph in the early 1900`s. Later he went on to create the comic strip Wonder Woman, a story about a displaced Amazon princess who forces anyone caught in her magic lasso to tell the truth",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/b8583f70c337e9723399acbf6cb05b2f"
        ),
        FactDto(
            id = "9bdc30bb257d1e50ec2fb504357cf66a",
            text = "Facetious and abstemious contain all the vowels in the correct order, as does arsenious, meaning \"containing arsenic.\"",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/9bdc30bb257d1e50ec2fb504357cf66a"
        ),
        FactDto(
            id = "67f7ce58134fb4db777af97b8d9acf0e",
            text = "NBA superstar Michael Jordan was originally cut from his high school basketball team.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/67f7ce58134fb4db777af97b8d9acf0e"
        ),
        FactDto(
            id = "5419081e89829517fd1db4776f86d92c",
            text = "Non-dairy creamer is flammable.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/5419081e89829517fd1db4776f86d92c"
        ),
        FactDto(
            id = "6c3e7ab9c687ba956c631ff580e97946",
            text = "An eighteenth-century German named Matthew Birchinger, known as \"the little man of Nuremberg,\" played four musical instruments including the bagpipes, was an expert calligrapher, and was the most famous stage magician of his day. He performed tricks with the cup and balls that have never been explained. Yet Birchinger had no hands, legs, or thighs, and was less than 29 inches tall.",
            source = "djtech.net",
            sourceUrl = "http://www.djtech.net/humor/useless_facts.htm",
            language = "en",
            permalink = "https://uselessfacts.jsph.pl/api/v2/facts/6c3e7ab9c687ba956c631ff580e97946"
        )
    )

    fun getData() = Single.just(factsList)
}