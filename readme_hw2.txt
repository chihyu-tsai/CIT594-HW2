/**********************************************************************
 *  README.txt                                                   
 *  hw1 Catch a Plagiarist
 **********************************************************************/

/**********************************************************************
* Name?
***********************************************************************/
Chih Yu Tsai

/**********************************************************************
* Checkpoint 1 Response
***********************************************************************/
next() - assuming the max word length for each word is m we are reading in n-grams. The
checking hasNext() is O(1).The time complexity for next() is O(n*m) and the space complexity is O(n*m)

processDocuments()
Assuming there are k files, each file has maximum l words, max word length is m,
reading in n grams, then the time complexity for processDocuments() is O(k * (l-n) * n * m).
This is linear time.
and the space complexity is O(k * (l-n) * n * m)

/**********************************************************************
* Checkpoint 2 Response
***********************************************************************/
storeNGrams()
Assuming there are k files, each file has maximum l words, max word length is m,
reading in n grams, then the time complexity for storeNGrams() is O(k * (l-n) * n * m).
This is linear time.
The space complexity is also O(k * (l-n) * n * m)

/**********************************************************************
* Checkpoint 3 Response
***********************************************************************/
ComputeSimilarities()
Assume we have r files, each file has maximum l words, so each file has (l-n) ngrams. To search in all similarities
object in the treeset, the searching process would take O(log(r^2)). The time complexity to create Similarity object
upfront would take O(r^2logr). The main loop operation to look up all nGrams for a similarities object associated with two
files would be O((r^2)*(l-n)*log(r^2)). So the overall time complexity would be O((r^2)*(l-n)*log(r^2)) + O(r^2logr).
Space complexity would be O(r^2 + (r^2)*(l-n)) since there's a total of r(l-n) ngrams and the mapping to r files would
be r^2*(l-n) and the r^2 account for the upfront creation of similarities object

/**********************************************************************
* Checkpoint 4 Response
***********************************************************************/
Assume that we have j similarities object in the treeSet, then essentially we are looping through the
entire treeSet, which is O(j). The upperbound of j would be r^2. Space complexity would be O(j)

/**********************************************************************
* Checkpoint 5 Response
***********************************************************************/
Assuming there are k files, each file has maximum l words, max word length is m,
reading in n grams, then the time complexity for processAndStore() is O(k * (l-n) * n * m).
It doesn't have a step to create a hashmap. However, the creation of a hashmap would only take
time complexity of O(k * (l-n) * n * m) to iterate through and O(1) to put. So the overall time
complexity in terms of Big-O stays the same.
The space complexity is also O(k * (l-n) * n * m)

/**********************************************************************
* Approximate number of hours to complete assignment?
***********************************************************************/
20 hours



/**********************************************************************
 * Describe any serious problems you encountered in this assignment.
 * What was hard, or what should we warn students about in the future?                    
 **********************************************************************/
I think it's good to explore, but it's also relatively complex. When it comes to analyze the time/ space
complexity, I think it's really overwhelming since these are complex methods and
I don't think it's an easy job based on our current capacity.


/**********************************************************************
 * List any other comments here. Feel free to provide any feedback   
 * on what you learned from doing the assignment, whether you enjoyed    
 * doing it, etc.
 **********************************************************************/
Maybe can use JUnit5 test