# Use Key, value pairing for word count
%data = ();

# Read file
my $file = "article.txt";
open( my $fh, "<", $file ) || die "Can't read file!";

# Output file
open( my $out, ">", "output.txt" ) || die "Can't create output file!";

while( my $line = <$fh> ) {
	my @words = split ' ', $line;
	
	# Append words to the word count dictionary
	foreach $a (@words) {
		# Remove special characters
		# Looks like some characters come out weird because of ANSI encoding
		$a =~ s/[\$#@~!&*()\[\];.,:?^ '"\\\/]+//g;		
		$b = lc $a;
		$data{$b} += 1;
	}
}

# For loop to sort the values from the data hash
foreach my $key (sort {$data{$b} <=> $data{$a}} keys %data) {
	my $println = $key . ": " . $data{$key} . "\n";
	print $out $println;
}
