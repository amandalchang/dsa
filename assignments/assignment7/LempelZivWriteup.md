## Lempel-Ziv Compression Benchmarking
My implementation of Lempel-Ziv compression is a string compression 
algorithm that exploits repetitiveness. The more times a substring is 
encountered, the more of it the algorithm will save until progressively
larger commonly occurring substrings become associated with single integers
within a generated codebook. 

It would therefore follow that the more repetitive a string is, the more
efficient the compression will be. The inverse is also true - that the more
unique and non-repetitive a string is, the less efficient LZ compression
should be. 

I created several input strings to test this hypothesis and also
characterized how efficient LZ compression would be in the general case with
sample text from The Bee Movie. The compression ratio is the file size of 
the compressed version over the original, so lower is better. 

My five inputs were:

    val peterShorExample = "AABABBBABAABABBBABBABB"
    val repetitive = "olin olin olin olin olin olin olin olin olin olin olin"
    val uniqueSent = "The quick brown fox jumps over the lazy dog"
    val alphabet = "abcdefgjijklmnopqrstuvwxyz"
    val beeMovieString = File(beeMoviePath).readText()

| Case |       Input       |         Description          | Compression Ratio (approximate) |
|:----:|:-----------------:|:----------------------------:|:-------------------------------:|
|  1   | peterShorExample | Limited variety & repetitive |              0.182              |
|  2   |     repetitive    |  Extremely repetitive case   |              0.333              |
|  3   |     uniqueSent    |       Unique sentence        |              0.814              |
|  4   |      alphabet     |   Alphabet with no spaces    |              1.077              |
|  5   |   beeMovieString  |    Content from bee movie    |              0.560              |

As qualitatively predicted, the most efficient case was case 1 because it 
was both repetitive and had a limited number of unique characters. The 
algorithm was able to represent large substrings with indexes and reuse them 
whenever those substrings reappeared. Additional savings came in with it only
having two unique characters, which is what made it more efficient than my 
second place string, case 2.

For similar reasons, alphabet is the least efficient. In fact, the compressed
version is larger than the original. This is because it had to continually
generate new indexes for every single character encountered. It never used
the index again after generating it, which is where the primary storage 
savings comes from for the LZ algorithm. Case 3 follows similar logic but
performed slightly better because the spaces between the words introduced
a repetitive element.

In the general case, as defined by the beeMovieString, my LZ algorithm had
a compression ratio of 0.560. This is likely fairly representative of the 
English language as a whole, because the Bee Movie sample was the entire
script. Conceptually, this makes sense since there are a limited number of
common words that make up our daily vocabulary. For instance, I'm sure that
the word "bees" has its own index within the codebook of the Bee Movie
instance of my class LempelZiv, and that it was used repeatedly once generated.