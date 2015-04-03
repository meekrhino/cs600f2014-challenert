# Read in raw data
jniinchi_119 <- read.csv( "C:/Users/Tristan/workspace/Comp/results/jniinchi-119.csv" )
jniinchi_137 <- read.csv( "C:/Users/Tristan/workspace/Comp/results/jniinchi-137.csv" )
jniinchi_149 <- read.csv( "C:/Users/Tristan/workspace/Comp/results/jniinchi-149.csv" )
jniinchi_194 <- read.csv( "C:/Users/Tristan/workspace/Comp/results/jniinchi-194.csv" )
jniinchi_197 <- read.csv( "C:/Users/Tristan/workspace/Comp/results/jniinchi-197.csv" )

# Merge data vertically
jniinchi_all <- rbind( jniinchi_119, jniinchi_137, jniinchi_149, jniinchi_194, jniinchi_197 )

# Add EXAM score to data frame
jniinchi_all$Exam <- jniinchi_all$Rank / jniinchi_all$StatementCount * 100

# Take subset of data set for only faulty statements
jniinchi_all_fault <- jniinchi_all[ which( jniinchi_all$IsFault == "true" ), ]

# Sort data by EXAM score
jniinchi_all_fault <- jniinchi_all_fault[ order( jniinchi_all_fault$Exam ), ]

# Split data back into case applications
jniinchi_119_fault <- jniinchi_all_fault[ which( jniinchi_all_fault$CaseApplication == "jniinchi-119" ), ] 
jniinchi_137_fault <- jniinchi_all_fault[ which( jniinchi_all_fault$CaseApplication == "jniinchi-137" ), ]
jniinchi_149_fault <- jniinchi_all_fault[ which( jniinchi_all_fault$CaseApplication == "jniinchi-149" ), ]
jniinchi_194_fault <- jniinchi_all_fault[ which( jniinchi_all_fault$CaseApplication == "jniinchi-194" ), ]
jniinchi_197_fault <- jniinchi_all_fault[ which( jniinchi_all_fault$CaseApplication == "jniinchi-197" ), ]

# Calculate average EXAM score for each function
j = jniinchi_all_fault[ which( jniinchi_all_fault$Function == "Jaccard" ), ]
jm = mean( j$Exam )

a = jniinchi_all_fault[ which( jniinchi_all_fault$Function == "Ample" ), ]
am = mean( a$Exam )

k = jniinchi_all_fault[ which( jniinchi_all_fault$Function == "Kulczynski 2" ), ]
km = mean( k$Exam )

o = jniinchi_all_fault[ which( jniinchi_all_fault$Function == "Ochiai" ), ]
om = mean( o$Exam )

t = jniinchi_all_fault[ which( jniinchi_all_fault$Function == "Tarantula" ), ]
tm = mean( t$Exam )

# Build data frame for average values

jniinchi_avg_fault = data.frame( Function=c( "Jaccard", "Ample", "Kulczynski 2", "Ochiai", "Tarantula" ), Exam=c( jm, am, km, om, tm ) )

# Generate graphs
library( ggplot2 )
attach( jniinchi_119_fault )
graph_119 <- qplot( Function, Exam, data=jniinchi_119_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score Per Risk Evaluation Function (JniInChi Mutant 119)",
				sub="JniInChi Mutant 119" )
graph_119 <- graph_119 + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_119_fault )

attach( jniinchi_137_fault )
graph_137 <- qplot( Function, Exam, data=jniinchi_137_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score Per Risk Evaluation Function (JniInChi Mutant 137)",
				sub="JniInChi Mutant 137" )
graph_137 <- graph_137 + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_137_fault )

attach( jniinchi_149_fault )
graph_149 <- qplot( Function, Exam, data=jniinchi_149_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score Per Risk Evaluation Function (JniInChi Mutant 149)",
				sub="JniInChi Mutant 149" )
graph_149 <- graph_149 + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_149_fault )

attach( jniinchi_194_fault )
graph_194 <- qplot( Function, Exam, data=jniinchi_194_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score by Risk Evaluation Function (JniInChi Mutant 194)",
				sub="JniInChi Mutant 194" )
graph_194 <- graph_194 + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_194_fault )

attach( jniinchi_197_fault )
graph_197 <- qplot( Function, Exam, data=jniinchi_197_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score by Risk Evaluation Function (JniInChi Mutant 197)",
				sub="JniInChi Mutant 197" )
graph_197 <- graph_197 + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_197_fault )

attach( jniinchi_avg_fault )
graph_avg  <- qplot( Function, Exam, data=jniinchi_avg_fault, geom="bar", 
			  	ylab="EXAM Score (%)", xlab="Risk Evaulation Function", 
				stat="identity", ylim=c( 0,5 ),
				main="EXAM Score by Risk Evaluation Function (JniInChi Mutant Average)",
				sub="JniInChi Mutant Average" )
graph_avg <- graph_avg + theme( axis.title=element_text( face="bold.italic" ) )
detach( jniinchi_avg_fault )

# Save graphs to thesis/img directory
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_119.pdf", plot=graph_119 )
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_137.pdf", plot=graph_137 )
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_149.pdf", plot=graph_149 )
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_194.pdf", plot=graph_194 )
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_197.pdf", plot=graph_197 )
ggsave( filename="C:/Users/Tristan/workspace/Comp/rsrc/thesis/AllegThesis/img/graph_avg.pdf", plot=graph_avg )