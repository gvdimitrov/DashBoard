use warnings;
use strict;

use Time::HiRes qw ( sleep );

my $url = $ARGV[0] // "http://165.227.134.146:8080/communication/send";


my $counter = 0;
my $distance = 0;


while(1)
{
    #Headlights
    if($counter % 20 == 0)
    {
        my $rand = int(rand(4));

        my $values = {
            low_beams_on => "false",
            high_beams_on => "false",
            fog_light_on => "false",
        };

        if($rand == 0)
        {
            $$values{low_beams_on} = "true";
        }
        elsif($rand == 1)
        {
            $$values{high_beams_on} = "true";
        }
        elsif($rand == 2)
        {
            $$values{low_beams_on} = "true";
            $$values{fog_light_on} = "true";
        }
        else
        {
            #Keeep default
        }   
    
        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"headlights","value":{"highBeams":$$values{high_beams_on},"lowBeams":$$values{low_beams_on},"fogLight":$$values{fog_light_on}}}' $url`;

    }

    #Gear
    if($counter % 30 == 0)
    {
        my $rand = int(rand(4));

        my $current = 4;
        my $next = 4;

        if($rand == 0)
        {
            $current = 4;
            $next = 5;            
        }
        elsif($rand == 1)
        {
            $current = 4;
            $next = 3;
        }
        else
        {
            #Keep the default
        }
        

        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"gear","value":{"currentGear":$current,"nextGear":$next}}' $url`;
    }

    #Trip 
    if(1)
    {
        $distance++;
        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"trip", "value": {"distance": $distance}}' $url`;

        if($distance == 9999)
        {
            $distance = 0;
        }
    }

    #Belt 
    if($counter % 40 == 0)
    {
        my $rand = int(rand(3));

        my $warning = "false";
        my $severity = "low";

        if($rand == 0)
        {
            $warning = "true";
        }
        elsif($rand == 1)
        {
            $warning = "true";
            $severity = "high";
        }
        else
        {
            #Keep default
        }
        
        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"belt","value":{"warningForSeatBelt":$warning,"warningSeverity":"$severity"}}' $url`;
    }

    #Parktronic 
    if($counter % 20 == 0)
    {
        my $rand = int(rand(4));

        my $front = 0;
        my $rear = 0;
        
        if($rand == 0)
        {
            $front = 3;
        }
        elsif($rand == 1)
        {
            $front = 6;
        }
        elsif($rand == 2)
        {
            $rear = 4;
        }
        elsif($rand == 3)
        {
            $rear = 5;
        }
        else
        {
            #Keep defaults
        }

        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"parktronic","value":{"front":$front,"rear":$rear}}' $url`;
    }  

    #Climatronic
    if($counter % 10 == 0)
    {
        my $rand = int(rand(3));
        
        my $current = 30;
        my $desired = 25;
        my $blower = 3;

        if($rand == 0)
        {
            $current = 29;
            $desired = 25;
            $blower = 2;
        }
        elsif($rand == 1)
        {
            $current = 27;
            $desired = 25;
            $blower = 1;
        }
        else
        {
            #Keep default
        }
        
        `curl -H 'Content-Type: application/json' -X POST -d '{"moduleName":"climatronic","value":{"currentDegrees":$current,"desiredDegrees":$desired,"blowerPower":$blower}}' $url`;

    }

    $counter++;

    if($counter == 1000)
    {
        $counter = 0;
    }

    sleep(0.01);
}





