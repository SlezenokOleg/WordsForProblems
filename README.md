I uses a Trie data structure which is a best fit for a problem domain like this,

it saves a lot of memory space.

Instead of using a HashSet, Trie could save all the word with the same prefix into same places
.
This same location advantage also speeds up the loop up process when searching a particular word/substring.

I implemented a remove() and undoRemove() method to mark one word off and on instead a creating a new copy 

of a HashSet to check every word.

In class TrieNode i realize 26 children, which corresponds to each letter of the English alphabet, which will be prefixes.


                                              Result:
                                              
                                 1)The largest concatenated word is: ethylenediaminetetraacetates.
                                 
                                 Length of first word is: 28.
                                 
                                 2)The second longest concatenated word is: ethylenediaminetetraacetate.
                                 
                                 Length of second word is: 27.    
                                 
                                 3)Total of 97107 valid concatenated words in file.
